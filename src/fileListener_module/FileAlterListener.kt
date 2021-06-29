package com.example.fileListener_module

import java.io.File

/**
 * @Description 文件变化监听
 * @author 裴晨栋
 * @date 2021/6/24-21:48
 */
class FileAlterListener {

    var start: (() -> Unit)? = null
    var stop: (() -> Unit)? = null
    var fileCreate: ((file: File) -> Unit)? = null
    var fileDelete: ((file: File) -> Unit)? = null
    var fileChange: ( (file:File) -> Unit)? = null
    var fileDirectoryCreate : ( (file:File) -> Unit)? = null
    var fileDirectoryDelete : ( (file:File) -> Unit)? = null
    var fileDirectoryChange : ( (file:File) -> Unit)? = null


    /**
     * 监听开始
     */
    fun start(block: () -> Unit) {
        this.start = block

    }

    /**
     * 创建新文件
     */
    fun fileCreate(block: (file: File) -> Unit) {
        this.fileCreate = block
    }

    /**
     * 文件更改
     */
    fun fileChange(block: (file: File) -> Unit) {
        this.fileChange = block
    }

    /**
     * 创建文件夹
     */
    fun fileDirectoryCreate(block: (file: File) -> Unit) {
        this.fileDirectoryCreate = block
    }


    /**
     * 文件被删除
     */
    fun fileDelete(block: (file: File) -> Unit) {
        this.fileDelete = block
    }

    /**
     * 文件夹被删除
     */
    fun fileDirectoryDelete(block: (file: File) -> Unit) {
        this.fileDirectoryDelete = block
    }
    /**
     * 文件夹被更改
     */
    fun fileDirectoryChange(block: (file: File) -> Unit) {
        this.fileDirectoryChange = block
    }

    /**
     * 监听结束
     */
    fun stop(block: () -> Unit) {
        this.stop = block
    }

}