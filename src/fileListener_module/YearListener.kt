package fileListener_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.table.BlogData
import com.example.fileListener_module.fileListener.FileAlterListener
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/7/28-20:51
 */
class YearListener : FileAlterListener {


    companion object {
        private val logger = LoggerFactory.getLogger(YearListener::class.java.name)

        /**
         * 判断是否是index.html文件
         */
        fun isIndexHtml(file: File?): Boolean {
            val name = file?.name
            if (name != "index.html") {
                return true
            }
            return false
        }

        private fun insertArticle(file: File?) {
            /**
             * 自底向上获取各级文件
             */
            //文章
            val parentFile = file?.parentFile
            //日
            val dayFile = parentFile?.parentFile
            //月
            val monthFile = dayFile?.parentFile
            //年
            val yearFile = monthFile?.parentFile

            val day = dayFile?.name
            val month = monthFile?.name
            val year = yearFile?.name
            if (!BlogDao.isContain(parentFile?.name)) {
                val blogData = BlogData(parentFile?.name,
                    "$year/$month/$day",
                    "",
                    "/$year/$month/$day/${parentFile?.name}/",
                    year?.toInt(),
                    month?.toInt(),
                    day?.toInt(),
                    file?.length()?.toInt())
                BlogDao.insertArticle(blogData)
                logger.info("文章 -- ${parentFile?.name} 添加")
            }
        }
    }

    override fun start(file: File?) {



        val name = file?.name
        val year: String? = name
        var month: String?
        var day: String?

        file?.listFiles()?.forEach { monthFile ->

            //月
            month = monthFile.name
            monthFile?.listFiles()?.forEach { dayFile ->

                //天
                day = dayFile.name
                dayFile?.listFiles()?.forEach {

                    val title = it.name
                    val length = it?.listFiles()?.first()?.length()
                    if (!BlogDao.isContain(it.name)) {
                        val blogData = BlogData(title,
                            "$year/$month/$day",
                            "",
                            "/$year/$month/$day/${it.name}/",
                            year = year?.toInt(),
                            month = month?.toInt(),
                            day = day?.toInt(),
                            length = length?.toInt())
                        BlogDao.insertArticle(blogData)
                        logger.info("文章 -- ${it.name} 添加")
                    }


                }
            }
        }

    }


    override fun fileCreate(file: File?) {
        if (isIndexHtml(file)) return

        insertArticle(file)
    }


    override fun fileChange(file: File?) {
        if (isIndexHtml(file)) return

        logger.info("${file?.parentFile?.name} 发送变化")
        insertArticle(file)
    }

    override fun fileDirectoryCreate(file: File?) {

    }

    override fun fileDelete(file: File?) {
        if (isIndexHtml(file)) return


        val parentFile = file?.parentFile
        val parentName = parentFile?.name

        logger.info("$parentName 文件删除")

        if (BlogDao.isContain(parentName)) {
            BlogDao.deleteArticle(parentName)
            logger.info("文章 $parentName 删除")
        }
    }


    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {

    }

    override fun stop(file: File?) {
        if (isIndexHtml(file)) return

        logger?.info("${file?.name} --- 监听结束")
    }
}