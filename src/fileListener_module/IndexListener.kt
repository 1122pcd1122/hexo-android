package fileListener_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.dao.UserDao
import com.example.fileListener_module.fileListener.FileAlterListener
import com.example.fileListener_module.fileListener.FileAlterObserver
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/7/31-17:35
 */
class IndexListener: FileAlterListener {

    companion object{
        private val logger = LoggerFactory.getLogger(IndexListener::class.java.name)

        private fun insertTitle(parse: Document) {
            val titleClass = parse.body().getElementsByClass("title")
            val title = titleClass[0].text()

            if (UserDao.queryBlogName().equals("")){
                UserDao.insertBlogName(title)
                logger.info("添加标题 -- $title")
            }
        }

        private fun insertSignature(parse: Document) {
            val signatureClass = parse.body().getElementsByClass("large")
            val signature = signatureClass[0].text()
            if (UserDao.querySignature().equals("")){
                UserDao.insertSignature(signature)
                logger.info("添加签名 -- $signature")
            }
        }

    }

    override fun start(file: File?) {


        val parse = Jsoup.parse(file, "UTF-8")


        insertTitle(parse)

        insertSignature(parse)
    }



    override fun fileCreate(file: File?) {

    }

    override fun fileChange(file: File?) {
        val parse = Jsoup.parse(file, "UTF-8")

        logger.info("信息发送变化---")

        insertTitle(parse)

        insertSignature(parse)
    }

    override fun fileDirectoryCreate(file: File?) {

    }

    override fun fileDelete(file: File?) {
        BlogDao.deleteArticle(file?.name)
    }

    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {

    }

    override fun stop(file: File?) {
        logger?.info("${file?.name} --- 监听结束")
    }
}