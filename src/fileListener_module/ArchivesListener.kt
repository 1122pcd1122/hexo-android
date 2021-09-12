package com.example.fileListener_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.table.BlogData
import com.example.fileListener_module.fileListener.FileAlterListener
import common_module.db.table.Blog
import fileListener_module.IndexListener
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Description
 * @author peichendong
 * @date 2021/8/31-21:42
 */
class ArchivesListener : FileAlterListener {
    companion object {
        private val logger = LoggerFactory.getLogger(ArchivesListener::class.java.name)
    }

    override fun start(file: File?) {
        logger.info("Archives文件夹开始监听......")
        file?.listFiles()?.forEach { file1 ->

            if (file1.name == "index.html"){

                val indexHtml = getIndexHtml(file1)

                val postListClass = indexHtml?.getElementsByClass("post-list")
                val postListItemClass = postListClass?.get(0)?.getElementsByClass("post-list-item")
                val lastTitleList = mutableListOf<String>()
                postListItemClass?.forEach {
                    val articleInfo = articleInfo(it)

                    if (!BlogDao.isContain(articleInfo.title)) {
                        logger.info("添加文章 ${articleInfo.title}")
                        BlogDao.insertArticle(articleInfo)
                    }else if (BlogDao.isChange(articleInfo)){
                        logger.info("更新文章 ${articleInfo.title}")
                        BlogDao.updateArticle(articleInfo)
                    }
                    lastTitleList.add(articleInfo.title.toString())
                }
                val listAllArticle = BlogDao.listAllArticleTitle()

                listAllArticle.forEach {
                    if (lastTitleList.indexOf(it) == -1){
                        logger.info("删除文章 $it")
                        BlogDao.deleteArticle(it)
                    }
                }
            }

        }

    }

    override fun fileCreate(file: File?) {
        if (file?.parentFile?.name != "archives"){
            return
        }
        val indexHtml = getIndexHtml(file)
        val postListClass = indexHtml?.getElementsByClass("post-list")
        val postListItemClass = postListClass?.get(0)?.getElementsByClass("post-list-item")
        val lastTitleList = mutableListOf<String>()
        postListItemClass?.forEach {
            val articleInfo = articleInfo(it)

            if (!BlogDao.isContain(articleInfo.title)) {
                logger.info("添加文章 ${articleInfo.title}")
                BlogDao.insertArticle(articleInfo)
            }else if (BlogDao.isChange(articleInfo)){
                logger.info("更新文章 ${articleInfo.title}")
                BlogDao.updateArticle(articleInfo)
            }
            lastTitleList.add(articleInfo.title.toString())
        }

        val listAllArticle = BlogDao.listAllArticleTitle()
        listAllArticle.forEach {
            if (lastTitleList.indexOf(it) == -1){
                logger.info("删除文章 $it")
                BlogDao.deleteArticle(it)
            }
        }
    }

    override fun fileChange(file: File?) {
        if (file?.parentFile?.name != "archives"){
            return
        }
        val indexHtml = getIndexHtml(file)


        val postListClass = indexHtml?.getElementsByClass("post-list")
        val postListItemClass = postListClass?.get(0)?.getElementsByClass("post-list-item")
        val lastTitleList = mutableListOf<String>()
        postListItemClass?.forEach {
            val articleInfo = articleInfo(it)
            lastTitleList.add(articleInfo.title.toString())

            if (!BlogDao.isContain(articleInfo.title)) {
                logger.info("添加文章 ${articleInfo.title}")
                BlogDao.insertArticle(articleInfo)
            }else if (BlogDao.isChange(articleInfo)){
                logger.info("更新文章 ${articleInfo.title}")
                BlogDao.updateArticle(articleInfo)
            }
        }

        val listAllArticle = BlogDao.listAllArticleTitle()
        listAllArticle.forEach {
            if (lastTitleList.indexOf(it) == -1){
                logger.info("删除文章 $it")
                BlogDao.deleteArticle(it)
            }
        }
    }

    override fun fileDelete(file: File?) {
        val indexHtml = getIndexHtml(file)

        val postListClass = indexHtml?.getElementsByClass("post-list")
        val postListItemClass = postListClass?.get(0)?.getElementsByClass("post-list-item")
        postListItemClass?.forEach {
            val articleInfo = articleInfo(it)

            if (!BlogDao.isContain(articleInfo.title)) {
                BlogDao.deleteArticle(file?.name)
            }
        }

    }

    override fun stop(file: File?) {
        logger.info("Archives文件夹监听结束......")
    }

    /**
     * 获取index.html
     */
    private fun getIndexHtml(file: File?): Document? {
        if (!file?.name.equals("index.html")) {

            return null
        }
        return Jsoup.parse(file, "UTF-8")
    }

    /**
     * 获取文章信息
     */
    private fun articleInfo(element: Element?): BlogData {
        val href = element?.getElementsByTag("a")?.attr("href")

        val contentClass = element?.getElementsByClass("content")
        val content = contentClass?.get(0)
        val titleClass = content?.getElementsByClass("title")
        val title = titleClass?.text()

        val timeClass = content?.getElementsByClass("time")
        val time = timeClass?.get(0)
        val spanClass = time?.getElementsByTag("span")
        val month = spanClass?.get(0)?.text()
        val day = spanClass?.get(1)?.text()?.get(0).toString()
        val year = spanClass?.get(2)?.text()

        return BlogData(title, href, year, month, day)
    }

    override fun fileDirectoryCreate(file: File?) {

    }

    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {

    }

}