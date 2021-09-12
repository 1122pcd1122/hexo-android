package com.example.fileListener_module

import com.example.common_module.db.dao.UserDao
import com.example.fileListener_module.fileListener.FileAlterListener
import com.example.user_module.logger
import org.jsoup.Jsoup
import java.io.File

/**
 * @Description
 * @author peichendong
 * @date 2021/8/14-20:02
 */
class AboutListener:FileAlterListener {
    companion object{
        private fun getIcon(file: File?):String{
            val parse = Jsoup.parse(file, "UTF-8")
            val content = parse.body().getElementsByClass("content")[0]
            return content.getElementsByTag("img").attr("src") ?: ""
        }
    }

    override fun start(file: File?) {
        file?.listFiles()?.forEach {
            if (it.name == "index.html"){
                UserDao.updateBlogIcon(getIcon(it))
                logger.info("更新头像")
            }
        }

    }

    override fun fileCreate(file: File?) {

    }

    override fun fileChange(file: File?) {
        if (file?.name == "index.html"){
            UserDao.updateBlogIcon(getIcon(file))
            logger.info("更新头像")
        }
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

    }
}