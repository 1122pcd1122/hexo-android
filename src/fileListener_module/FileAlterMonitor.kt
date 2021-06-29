package com.example.fileListener_module

import kotlinx.coroutines.*

/**
 * @Description 监听管理器
 * @author 裴晨栋
 * @date 2021/6/24-21:49
 */
class FileAlterMonitor {

    /**
     * 每次的轮询间隔
     */
    private var interval: Long = 1000

    /**
     * 文件监听观察者
     */
    private var observers: MutableList<FileAlterObserver> = mutableListOf()

    /**
     * 协程域
     */
    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * 工作内容
     */
    private var launch: Job? = null

    /**
     * 是否允许
     */
    private var running = false

    /**
     * 构造器 根据轮询间隔来创建构造器
     *
     * @param interval 轮询间隔
     */
    constructor(interval: Long? = null) : this(interval, null)

    /**
     * 构造器 轮询间隔和观察者来创建构造器
     *
     * @param interval 轮询间隔
     * @param observers 观察者
     */
    constructor(interval: Long? = null, observers: List<FileAlterObserver>? = null) {
        if (interval != null) {
            this.interval = interval
        }
        observers?.forEach {
            this.observers.add(it)
        }
    }


    /**
     *
     * 获取轮询间隔
     * @return 轮询间隔
     */
    fun getInterval(): Long {
        return interval
    }

    /**
     * 向此监视器添加文件系统观察器。
     *
     * @param observer 要添加的文件系统观察者
     */
    fun addObserver(observer: FileAlterObserver?) {
        if (observer != null) {
            observers.add(observer)
        }
    }

    /**
     * 从此监视器中删除文件系统观察者
     *
     * @param observer 要删除的文件系统观察者
     */
    fun removeObserver(observer: FileAlterObserver?) {
        if (observer != null) {
            while (observers.remove(observer)) {
                // empty
            }
        }
    }

    /**
     * 返回在此监视器中注册的 [FileAlterObserver] 集。
     *
     * @return 一套 [FileAlterObserver]
     */
    fun getObservers(): Iterable<FileAlterObserver?> {
        return observers
    }


    /**
     * 开始运行此监视器
     */
    fun start() {
        if (running) {
            throw IllegalStateException("Monitor is already running")
        }

        for (observer in observers) {
            observer.initialize()
            observer.startObserver()
        }

        running = true


        run()
    }

    /**
     * 正式运行
     */
    private fun run() {
        coroutineScope.launch {
            while (running) {
                for (observer in observers) {
                    observer.checkAndNotify()
                }

                if (!running) {
                    break
                }

                delay(interval)
            }
        }
    }

    /**
     * 停止
     */
    fun stop() {
        if (!running) {
            throw IllegalStateException("Monitor is not running")
        }

        running = false
        launch?.cancel()
        observers.forEach {
            it.stopObserver()
        }
    }


}