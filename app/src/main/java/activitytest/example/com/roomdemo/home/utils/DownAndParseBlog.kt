package activitytest.example.com.roomdemo.home.utils

import activitytest.example.com.roomdemo.home.bean.Root
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle
import activitytest.example.com.roomdemo.home.viewmodel.BlogViewModel
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce
import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*


/*
    下载并解析MarkDown文件为html文件
 */
@Suppress("COMPATIBILITY_WARNING", "TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class DownAndParseBlog(
        private val blogViewModel: BlogViewModel? = null,
        private val lifecycleOwner: LifecycleOwner? = null
) {

    /*
        日志名
     */
    private val downAndParseBlogTag:String = this::class.java.name

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
        lifecycleOwner?.let { it ->
            blogViewModel?.blogInfoLiveData?.observe(it, Observer {
            listBlogIntroduce(it)
        })
        }
    }

    /*
        下载
     */
    private fun listBlogIntroduce(roots: List<Root?>?):MutableLiveData<List<BlogIntroduce>>{
        val downloadUrlList: MutableList<String?> = ArrayList()
        if (roots != null) {
            for (i in roots.indices) {
                val downloadUrl = roots[i]?.download_url
                downloadUrlList.add(downloadUrl)
            }
        }

        val blogIntroduceList: ArrayList<BlogIntroduce> = ArrayList()
        lifecycleOwner?.let {
            blogViewModel?.listBlogContent(downloadUrlList)?.observe(it, { strings ->
                if (strings != null) {
                    for (html in strings) {
                        val parse = Jsoup.parse(html)
                        //解析标题
                        val title = parseTitle(parse)
                        //解析时间
                        val time = parseUpDateTime(parse)
                        val tags = parseCategories(parse)
                        blogIntroduceList.add(BlogIntroduce(title, time, html, tags))
                    }
                    Log.d(downAndParseBlogTag,"开始添加...")
                    blogIntroduceLiveData.postValue(blogIntroduceList)
                }

            })
        }

        return blogIntroduceLiveData
    }
    /*
        插入数据库
     */
    suspend fun insertInoDB(list: List<BlogIntroduce>) {
        val toTypedArray = list.toTypedArray()
        val blogSet = mutableSetOf<Blog>()
        val tagSet = mutableSetOf<String>()

        for (value in toTypedArray) {
            val blogTitle = value.blogTitle
            val updateTime = value.time
            val html = value.html
            val tags = value.tags
            //添加博客内容
            blogSet.add(Blog(blogTitle, blogTitle+"",updateTime,html))
            //添加标签
            tags.forEach {
                tagSet.add(it)
            }

            Log.d(downAndParseBlogTag,"获取数据中")
        }

        //添加博客
        val blogDao = MyDatabase.instance!!.blogDao
        //添加博客文章
        val queryBlog = blogDao.queryBlogId()
        GlobalScope.launch(Dispatchers.Main) {
            queryBlog.observe(lifecycleOwner!!, Observer {
                Log.d(downAndParseBlogTag,"添加中......-blog")
                for (item in blogSet) {
                    if (!it.contains(item.blogId)){
                        GlobalScope.launch {
                            blogDao.insertBlog(item)
                        }
                    }
                }
                Log.d(downAndParseBlogTag,"添加成功-blog")
            })
        }

        //添加标签
        val tagsDao = MyDatabase.instance!!.tagsDao
        val queryTagName = tagsDao.queryTagName()
        GlobalScope.launch(Dispatchers.Main) {
            queryTagName?.observe(lifecycleOwner!!, Observer {
                for (item in tagSet) {
                    if (it?.contains(item) == false){
                        val tag = Tags(item,item)
                        GlobalScope.launch {
                            tagsDao.insertTags(tag)
                        }
                    }
                }
                Log.d(downAndParseBlogTag,"添加完成-tag")
            })

        }
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

}






