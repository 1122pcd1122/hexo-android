package activitytest.example.com.roomdemo.home.utils

import activitytest.example.com.roomdemo.home.adapter.BlogAdapter
import activitytest.example.com.roomdemo.home.bean.Root
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle
import activitytest.example.com.roomdemo.home.viewmodel.BlogViewModel
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce
import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.database.dao.BlogDao
import activitytest.example.com.roomdemo.main.database.dao.BlogTagsDao
import activitytest.example.com.roomdemo.main.database.dao.TagsDao
import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.ArrayList

@Suppress("COMPATIBILITY_WARNING")
class DownAndParseBlog(private var blogViewModel: BlogViewModel? = null, private val lifecycleOwner: LifecycleOwner? = null) {

    val TAG:String = this::class.java.name

    private val blogIntroduceLiveData :MutableLiveData<List<BlogIntroduce>> by lazy {
        MutableLiveData<List<BlogIntroduce>>()
    }



     fun listDownLoadUrls(){
        blogViewModel?.init()
        lifecycleOwner?.let {
            blogViewModel?.blogInfoLiveData?.observe(it, Observer {
            listBlogIntroduce(it)
        })
        }
    }

     fun listBlogIntroduce(roots: List<Root?>?):MutableLiveData<List<BlogIntroduce>>{
        val downloadUrlList: MutableList<String?> = ArrayList()
        if (roots != null) {
            for (i in roots.indices) {
                val downloadUrl = roots[i]?.download_url
                downloadUrlList.add(downloadUrl)
            }
        }

        val blogIntroduceList: ArrayList<BlogIntroduce> = ArrayList()
        lifecycleOwner?.let {
            blogViewModel?.listBlogContent(downloadUrlList)?.observe(lifecycleOwner, { strings ->
                if (strings != null) {
                    for (html in strings) {
                        val parse = Jsoup.parse(html)
                        //解析标题
                        val title = parseTitle(parse)
                        //解析时间
                        val time = parseUpDateTime(parse)
                        val tags = parseCategories(parse)
                        blogIntroduceList.add(BlogIntroduce(title, MonthUtil.getNowDate(time), html, tags))
                    }
                    Log.d(TAG,"开始添加...")
                    InsertBlogAsyncTask(MyDatabase.instance?.blogDao!!, MyDatabase.instance?.tagsDao!!, MyDatabase.instance?.blogTagsDao!!).execute(blogIntroduceList)

                    blogIntroduceLiveData.postValue(blogIntroduceList)
                }

            })
        }

        return blogIntroduceLiveData
    }

    fun listBlog():LiveData<List<BlogIntroduce>>{
        return blogIntroduceLiveData
    }

    private fun parseCategories(parse: Document): Array<String> {
        val tags = parse.getElementsByTag("code")
        val element = tags[0]
        val split = element.text().split("\n").toTypedArray()
        Log.d(HomeLifeCycle.TAG, "标签字符串:" + element.text())
        for (i in split.indices) {
            val split1 = split[i].split("-").toTypedArray()
            split[i] = split1[1]
        }
        Log.d(HomeLifeCycle.TAG, "标签" + split.contentToString())
        return split
    }

    /**
     * @param parse 文档
     * @return 时间
     */
    private fun parseUpDateTime(parse: Document): String {
        val h2 = parse.getElementsByTag("h2")
        val timeElement = h2[0]
        return timeElement.text()
    }

    /**
     * @param parse 文档
     * @return 标题
     */
    private fun parseTitle(parse: Document): String {
        val h1 = parse.getElementsByTag("p")
        val titleElement = h1[0]
        val title = titleElement.text()
        return title.substring(6, title.length - 6)
    }

    private class InsertBlogAsyncTask(private val blogDao: BlogDao, private val tagsDao: TagsDao, private val blogTagsDao: BlogTagsDao) : AsyncTask<List<BlogIntroduce>?, Int?, Int?>() {
        val TAG:String = this::class.java.name

        override fun doInBackground(vararg params: List<BlogIntroduce>?): Int {
            val array:Array<Blog?> = Array(params.size){ null }
            for ((index,value ) in params.withIndex()){
                val blogTitle = value?.get(index)?.blogTitle
                val updateTime = value?.get(index)?.time
                val html = value?.get(index)?.html
                val tags = value?.get(index)?.tags
                //添加博客内容
                array[index] = Blog(blogTitle+value, blogTitle.toString()+"",updateTime,html)
                //添加标签
                tags?.forEach {
                    Log.d(TAG,"添加中-tags")
                    val tag = Tags(it,it)
                    tagsDao.insertTags(arrayOf(tag))
                }
            }
            blogDao.insertBlog(array)
            Log.d(TAG,"添加成功-blog")

            return 0
        }


    }
}






