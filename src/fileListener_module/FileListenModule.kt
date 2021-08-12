package com.example.fileListener_module

import com.example.common_module.utils.ConfUtil
import com.example.fileListener_module.fileListener.FileAlterMonitor
import com.example.fileListener_module.fileListener.FileAlterObserver
import fileListener_module.BlogFileListener
import io.ktor.application.*
import org.slf4j.LoggerFactory
import java.io.*
import java.util.*

fun Application.fileListen(){

    LoggerFactory.getLogger("文件监听模块")


    //根文件观察者
    val fileAlterObserver = FileAlterObserver(ConfUtil.mdPath.toString())

    //为观察者添加监听器
    fileAlterObserver.addListener(BlogFileListener())

    //监控器
    val fileAlterMonitor = FileAlterMonitor()
    //监控器添加 观察者
    fileAlterMonitor.addObserver(fileAlterObserver)

    //监控器开始工作
    fileAlterMonitor.start()

}

