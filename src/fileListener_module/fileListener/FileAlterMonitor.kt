package com.example.fileListener_module.fileListener

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
     * 设置轮询间隔
     */
   fun setInterval(interval:Long){
       this.interval = interval
   }


    /**
     * 添加观察者列表
     */
    fun addObservers(observers: List<FileAlterObserver>?){
        this.observers = observers as MutableList<FileAlterObserver>
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