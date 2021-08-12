package fileListener_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.dao.LabelDao
import com.example.common_module.db.dao.UserDao
import com.example.common_module.db.table.BlogData
import com.example.fileListener_module.fileListener.FileAlterListener
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/7/28-20:50
 */
class TagsListener : FileAlterListener {


    private var rootFile: File? = null
    companion object {
        private val logger = LoggerFactory.getLogger(TagsListener::class.java.name)
        /**
         * 判断是否是文件夹
         */
        private fun isDirectory(file: File?): Boolean {

            return file?.isDirectory!!
        }


    }

    override fun start(file: File?) {

        logger.info("标签监听中...... 文件路径 -- ${file?.absolutePath}")

        rootFile = file

        file?.listFiles()?.forEach {
            insertTag(it)
            updateArticleTag(it)
        }
    }

    /**
     * 添加标签
     * @param file tags文件夹
     */
    private fun insertTag(file: File?) {
        //文件名
        val name = file?.name
        if (!LabelDao.isContain(name)) {
            LabelDao.insertTag(name)
            logger.info("新增标签 -- $name")
        }


    }

    override fun fileCreate(file: File?) {
    }

    override fun fileChange(file: File?) {
    }

    override fun fileDirectoryCreate(file: File?) {
        logger.info("fileDirectoryCreate"+file?.name)
        //判断是否是文件夹 如果不是 直接退出
        if (!isDirectory(file)) return

        //因为标签名为文件夹名 每次添加一个新的标签会生成一个对应标签的文件夹
        val name = file?.name
        logger.info("标签创建 -- $name ")

        insertTag(file)

        updateArticleTag(file)
    }

    override fun fileDelete(file: File?) {
        val name = file?.parentFile?.name
        if (LabelDao.isContain(name)){
            LabelDao.deleteLabel(name)
            logger.info("删除标签 -- $name")
        }
    }

    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {
        if (!isDirectory(file)) return

        insertTag(file)
        updateArticleTag(file)

    }

    override fun stop(file: File?) {
        if (isDirectory(file)) return


        logger?.info("${file?.name} --- 监听结束")
    }

    /**
     * 更新文章标签
     * @param file 标签文件夹
     */
    private fun updateArticleTag(file: File?) {
        //获取index文件
        val index = file?.listFiles()?.first()
        //标签名
        val tagName = file?.name
        val parse = Jsoup.parse(index, "UTF-8")
        val postListItem = parse.body().getElementsByClass("post-list-item")


        postListItem.forEach {

            //htmlUrl
            val htmlUrl = it.getElementsByTag("a").attr("href")
            //时间
            val time = htmlUrl.split("/")
            //标题
            val content = it.getElementsByClass("content")
            val title = content[0].getElementsByClass("title").text()

            if (BlogDao.isContain(title) && !BlogDao.getTag(title).equals(tagName)) {
                BlogDao.updateTags(title, tagName)
                logger.info("${title}更新标签 -- $tagName")
            } else if (!BlogDao.isContain(title)) {
                if (UserDao.queryBlogName() != title) {
                    BlogDao.insertArticle(BlogData(title = title,
                        tags = tagName,
                        day =time[3].toInt(),
                        date = "${time[1]}/${time[2]}/${time[3]}",
                        htmlUrl = htmlUrl,
                        year = time[1].toInt(),
                        month = time[2].toInt(),
                        length = 0))
                    logger.info("添加新文章 $title 标签 -- $tagName")
                }
            }
        }
    }


}


