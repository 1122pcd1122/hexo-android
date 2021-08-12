package com.example.fileListener_module.fileListener

import java.io.File

/**
 * @Description 文件变化监听
 * @author 裴晨栋
 * @date 2021/6/24-21:48
 */
interface FileAlterListener {




    /**
     * 监听开始
     */
    fun start(file: File?)

    /**
     * 创建新文件
     */
    fun fileCreate(file: File?)

    /**
     * 文件更改
     */
    fun fileChange(file: File?)

    /**
     * 创建文件夹
     */
    fun fileDirectoryCreate(file: File?)


    /**
     * 文件被删除
     */
    fun fileDelete(file: File?)

    /**
     * 文件夹被删除
     */
    fun fileDirectoryDelete(file: File?)
    /**
     * 文件夹被更改
     */
    fun fileDirectoryChange(file: File?)

    /**
     * 监听结束
     */
    fun stop(file: File?)



}