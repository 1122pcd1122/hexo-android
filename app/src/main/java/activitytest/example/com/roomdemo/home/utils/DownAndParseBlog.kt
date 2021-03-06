package activitytest.example.com.roomdemo.home.utils

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
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*
import kotlin.collections.Set as Set1


/*
    下载并解析MarkDown文件为html文件
 */
@Suppress("COMPATIBILITY_WARNING", "TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class DownAndParseBlog(
        private var blogViewModel: BlogViewModel? = null,
        private val lifecycleOwner: LifecycleOwner? = null
) {

    /*
        日志名
     */
    val TAG:String = this::class.java.name

    /*
        blogIntroduceLiveDate初始化
     */
    private val blogIntroduceLiveData :MutableLiveData<List<BlogIntroduce>> by lazy {
        MutableLiveData<List<BlogIntroduce>>()
    }



    /*
        观察获取的所有博文的url信息并去下载markdown
     */
     fun listDownLoadUrls(){
        blogViewModel?.init()
        lifecycleOwner?.let {
            blogViewModel?.blogInfoLiveData?.observe(it, Observer {
            listBlogIntroduce(it)
        })
        }
    }

    /*
        下载
     */
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


                    blogIntroduceLiveData.postValue(blogIntroduceList)
                }

            })
        }

        return blogIntroduceLiveData
    }
    /*
        插入数据库
     */
    fun insertInoDB(list: List<BlogIntroduce>) {
        val toTypedArray = list.toTypedArray()
        InsertBlogAsyncTask(MyDatabase.instance?.blogDao!!, MyDatabase.instance?.tagsDao!!, MyDatabase.instance?.blogTagsDao!!).execute(*toTypedArray)
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

    /*
        blogIntroduce和tag插入数据库
     */
    private class InsertBlogAsyncTask(private val blogDao: BlogDao, private val tagsDao: TagsDao, private val blogTagsDao: BlogTagsDao) : AsyncTask<BlogIntroduce?, Int?, Int?>() {
        val TAG:String = this::class.java.name


        override fun doInBackground(vararg params: BlogIntroduce?): Int {
            val blogSet = mutableSetOf<Blog>()
            val tagSet = mutableSetOf<String>()
            for ((index,value ) in params.withIndex()){
                val blogTitle = value?.blogTitle
                val updateTime = value?.time
                val html = value?.html
                val tags = value?.tags
                //添加博客内容
                blogSet.add(Blog(blogTitle.toString(), blogTitle.toString()+"",updateTime,html))
                //添加标签
                tags?.forEach {
                    tagSet.add(it)
                }

                Log.d(TAG,"获取数据中")
            }

            GlobalScope.launch(Dispatchers.Main) {
                //添加博客文章
                blogDao.queryBlogId().observeForever {
                    Log.d(TAG,"添加中......-blog")
                    val blogDao = MyDatabase.instance!!.blogDao
                    for (item in blogSet) {
                        if (!it.contains(item.blogId)){
                            blogDao.insertBlog(item)
                        }
                    }
                    Log.d(TAG,"添加成功-blog")
                }
            }
            GlobalScope.launch(Dispatchers.Main) {
                val tagsDao = MyDatabase.instance!!.tagsDao
                tagsDao.queryTagName().observeForever {

                    for (item in tagSet) {
                        if (!it.contains(item)){
                            val tag = Tags(item,item)
                            tagsDao.insertTags(tag)

                        }
                    }
                }
                Log.d(TAG,"添加完成-tag")

            }

            return 0
        }




    }
}






