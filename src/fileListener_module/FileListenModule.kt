package com.example.fileListener_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.table.BlogData
import com.example.common_module.utils.ConfUtil
import common_module.utils.TimeUtil
import io.ktor.application.*
import org.jsoup.Jsoup
import org.markdown4j.Markdown4jProcessor
import org.slf4j.LoggerFactory
import java.io.*
import java.util.*

fun Application.fileListen(){

    val logger = LoggerFactory.getLogger("文件监听模块")

    val fileAlterListener = FileAlterListener()

    /**
     * 文件监听开始
     */
    fileAlterListener.start {
        println("监听开始 开始在${Thread.currentThread().name}线程")
    }

    /**
     * 文件被创建
     */
    fileAlterListener.fileCreate {
        println("${Thread.currentThread().name}执行,文件:${it.name}创建")

        val name = it.name
        //将文件名 ***.md  拆为 *** 和 .md title[0] = *** title[1] = .md
        val title = name.split(".")

        val curDate = getCurDate()

        val htmlFile = writToHtml(it, title)

        // 获取标签
        val label = getLabels(htmlFile.first)

        val year = TimeUtil.getYear(curDate.first)
        val month = TimeUtil.getMonth(curDate.first)
        val day = TimeUtil.getDay(curDate.first)

       if (!BlogDao.isContain(title[0])){
           val blogData = BlogData(title[0], curDate.second, label, title[0] + ".html", year, month, day,htmlFile.second.length().toInt())
           BlogDao.insertArticleInfo(blogData)
       }else{
           logger.info("${name}已经被创建")
       }


    }

    /**
     * 文件修改
     */
    fileAlterListener.fileChange {
        println("${Thread.currentThread().name}执行,文件:${it.name} 变化")

        val name = it.name
        val title = name.split(".")

        val curDate = getCurDate()
        val htmlFile = writToHtml(it, title)
        // 获取标签
        val label = getLabels(htmlFile.first)

        val year = TimeUtil.getYear(curDate.first)
        val month = TimeUtil.getMonth(curDate.first)
        val day = TimeUtil.getDay(curDate.first)

        if (BlogDao.isContain(title[0])){
            val blogData = BlogData(title[0], curDate.second, label, title[0] + ".html", year, month, day,htmlFile.second.length().toInt())
            BlogDao.insertArticleInfo(blogData)
        }else{
            logger.info("${name}不存在")
        }

    }

    /**
     * 文件删除
     */
    fileAlterListener.fileDelete {
        println("${Thread.currentThread().name}执行,文件:${it.name} 删除")

        val name = it.name
        val title = name.split(".")

        if (!BlogDao.isContain(title = title[0])){
            logger.info("${name}已经被删除")
        }else {
            BlogDao.deleteArticle(title = title[0])
        }

    }

    /**
     * 文件夹创建
     */
    fileAlterListener.fileDirectoryCreate {
        println("${Thread.currentThread().name}执行,文件夹:${it.name} 创建")
    }


    /**
     * 文件夹改变
     */
    fileAlterListener.fileDirectoryChange {
        println("${Thread.currentThread().name}执行,文件夹:${it.name} 发生变化")
    }

    /**
     * 文件夹删除
     */
    fileAlterListener.fileDirectoryDelete{
        println("${Thread.currentThread().name}执行,文件夹:${it.name} 删除")
    }


    /**
     * 监听结束
     */
    fileAlterListener.stop {
        println("监听结束")
    }

    //获取观察者
    val fileAlterObserver = FileAlterObserver(ConfUtil.mdPath.toString())

    //为观察者添加监听器
    fileAlterObserver.addListener(fileAlterListener)

    //监控器
    val fileAlterMonitor = FileAlterMonitor()
    //监控器添加 观察者
    fileAlterMonitor.addObserver(fileAlterObserver)

    //监控器开始工作
    fileAlterMonitor.start()

}

private fun writToHtml(
    it: File,
    title: List<String>
): Pair<String, File> {
    val html = Markdown4jProcessor().process(it)
    val file = File(ConfUtil.htmlPath + "\\" + title[0] + ".html")
    val bufferedWriter = BufferedWriter(FileWriter(file))
    bufferedWriter.write(html)
    bufferedWriter.close()
    return Pair(html, file)
}

private fun getCurDate(): Pair<Date, String?> {
    //日期字符串 yyyy/MM/DD
    val date = Date()
    val time = TimeUtil.parseDate(date = date)
    return Pair(date, time)
}

private fun getLabels(html: String?): String? {
    val parse = Jsoup.parse(html)
    //文章标签所对应的html标签
    val tag = parse.getElementsByTag("p")
    //标签
    return tag[0].text()
}