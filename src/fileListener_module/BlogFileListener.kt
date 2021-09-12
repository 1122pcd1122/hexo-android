package fileListener_module

import com.example.common_module.utils.ConfUtil
import com.example.fileListener_module.AboutListener
import com.example.fileListener_module.ArchivesListener
import com.example.fileListener_module.fileListener.FileAlterListener
import com.example.fileListener_module.fileListener.FileAlterMonitor
import com.example.fileListener_module.fileListener.FileAlterObserver
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @author pcd
 * @Description TODO
 * @date 2021/7/28-19:09
 */
class BlogFileListener : FileAlterListener {

    companion object {

        private val logger = LoggerFactory.getLogger(BlogFileListener::class.java.name)

        /**
         * 根据不同的环境获取文件名
         *
         * 本地环境是 windows
         * 服务器环境是 Linux
         * java在二者环境中获取文件名的结果不同
         */
        private fun getFileName(it: File): String? {
            //判断环境 是否是本地环境
            return if (ConfUtil.env == "LOCAL") {
                it.name
            } else {
                it.name.split("\\")[0]
            }

        }

        /**
         * 设置子文件的观察者
         */
        private fun setObserver(
            absolutePath: String?,
            name: String?,
        ): FileAlterObserver {

            val path: String = if (ConfUtil.env == "LOCAL") {
                absolutePath + "\\${name}"
            } else {
                absolutePath + "/${name}"
            }

            logger.info("环境 -- ${ConfUtil.env} 设置观察者,观察路径 -- $path")

            return FileAlterObserver(path)
        }
    }


    override fun start(file: File?) {
        val absolutePath = file?.absolutePath
        logger?.info("监听开始...")
        logger?.info("根文件路径为 --- $absolutePath")


        file?.listFiles()?.forEach {

            when (val name = getFileName(it)) {
                "index.html" -> {
                    //添加index.html监听
                    val indexObserver: FileAlterObserver = setObserver(absolutePath, name)

                    indexObserver.addListener(IndexListener())
                    FileAlterMonitor().apply {
                        addObserver(indexObserver)
                        start()
                        logger?.info("index.html监听开始 文件名 -- $name")
                    }

                }
                "tags" -> {
                    //添加标签观察者
                    val tagsObserver: FileAlterObserver = setObserver(absolutePath, name)
                    tagsObserver.addListener(TagsListener())

                    FileAlterMonitor().apply {
                        addObserver(tagsObserver)
                        start()
                        logger?.info("标签监听开始 文件名 -- $name ")
                    }

                }
                "about" -> {
                    val aboutObserver = setObserver(absolutePath, name)
                    aboutObserver.addListener(AboutListener())

                    FileAlterMonitor().apply {
                        addObserver(aboutObserver)
                        start()
                        logger?.info("about监听开始 文件名 -- $name")
                    }
                }
                "archives" -> {
                    val archivesObserver = setObserver(absolutePath,name)
                    archivesObserver.addListener(ArchivesListener())
                    FileAlterMonitor().apply {
                        addObserver(archivesObserver)
                        start()
                        logger?.info("archives监听开始 文件名 -- $name")
                    }
                }
            }

        }
    }

    override fun fileCreate(file: File?) {

    }

    override fun fileChange(file: File?) {

    }

    override fun fileDirectoryCreate(file: File?) {

    }

    override fun fileDelete(file: File?) {

    }

    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {

    }


    override fun stop(file: File?) {
        logger?.info("${file?.name} --- 监听结束")
    }

}