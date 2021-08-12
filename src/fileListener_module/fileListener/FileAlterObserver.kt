package com.example.fileListener_module.fileListener


import java.io.File
import java.io.FileFilter
import java.util.*

/**
 * @Description 文件通知观察者
 * @author
 * @date 2021/6/24-21:48
 */
class FileAlterObserver() {


    /**
     * 监听器
     */
    private val listeners: MutableList<FileAlterListener> = mutableListOf()

    /**
     * 观察的文件对象
     */
    private var rootEntry: MyFile? = null

    /**
     * 文件过滤器
     */
    private var fileFilter: FileFilter? = null

    /**
     * 文件比较器
     */
    private var comparator: Comparator<File>? = null


    /**
     * 构造器 通过一个目录来构造观察者
     *
     * @param directoryName 要观察的目录的名称
     */
    constructor(directoryName: String) : this(File(directoryName), null)


    /**
     * 构造器 通过目录名和文件过滤器来构造观察者
     *
     * @param directoryName 要观察的目录的名称
     * @param fileFilter 文件过滤对象
     */
    constructor(directoryName: String, fileFilter: FileFilter?) : this(File(directoryName), fileFilter)

    /**
     * 构造器 通过一个文件对象来构造观察者
     *
     * @param directory 要观察的文件对象
     */
    constructor(directory: File?) : this(directory, null)


    /**
     * 构造器 通过文件对象和文件过滤器来构造观察者
     *
     * @param directory 要观察的文件对象
     * @param fileFilter 文件过滤对象
     */
    constructor(directory: File?, fileFilter: FileFilter?) : this(MyFile(directory), fileFilter)

    /**
     * 为指定的目录、文件过滤器和文件比较器构造观察者
     *
     * @param rootEntry 要观察的根目录
     * @param fileFilter 文件过滤器或 null 如果没有
     */
    constructor(rootEntry: MyFile?, fileFilter: FileFilter?, comparator: Comparator<File>? = FileComparator()) : this() {
        requireNotNull(rootEntry) { "Root entry is missing" }
        requireNotNull(rootEntry.getFile()) { "Root directory is missing" }
        this.rootEntry = rootEntry
        this.fileFilter = fileFilter

        this.comparator = comparator

    }

    /**
     * 返回正在观察的目录
     *
     * @return 被观察的目录
     */
    fun getDirectory(): File? {
        return rootEntry!!.getFile()
    }

    /**
     * 返回文件过滤器
     *
     * @return 文件过滤器
     */
    fun getFileFilter(): FileFilter? {
        return fileFilter
    }

    /**
     *添加文件系统侦听器
     *
     * @param listener 文件系统监听器
     */
    fun addListener(listener: FileAlterListener?) {
        if (listener != null) {
            listeners.add(listener)
        }
    }

    /**
     * 删除文件系统监听器
     *
     * @param listener 文件系统监听器
     */
    private fun removeListener(listener: FileAlterListener?) {
        if (listener != null) {
            while (listeners.remove(listener)) {
                // empty
            }
        }
    }

    /**
     * 返回已注册的文件系统侦听器集。
     *
     * @return 文件系统侦听器
     */
    fun getListeners(): Iterable<FileAlterListener?> {
        return listeners
    }


    /**
     * 初始化观察者
     *
     * @throws Exception if an error occurs
     */
    @Throws(Exception::class)
    fun initialize() {
        rootEntry!!.refresh(rootEntry!!.getFile())
        val children: Array<MyFile> = doListFiles(rootEntry!!.getFile(), rootEntry!!)
        rootEntry!!.setChildren(children)
    }

    @Throws(Exception::class)
    fun startObserver() {

        for (listener in listeners) {
            listener.start(rootEntry?.getFile())
        }
    }

    /**
     * 检查文件及其子文件是否已被创建、修改或删除。
     */
    fun checkAndNotify() {


        //获取当前文件状态
        val rootFile = rootEntry?.getFile()

        if (rootFile != null) {
            when (rootFile.exists()) {
                true -> checkAndNotify(rootEntry!!, rootEntry!!.getChildren(), listFiles(rootFile))

                false -> when (rootEntry!!.isExists()) {
                    true -> checkAndNotify(rootEntry!!, rootEntry!!.getChildren(), FileUtil.EMPTY_FILE_ARRAY)
                }
            }

        }


    }

    @Throws(Exception::class)
    fun stopObserver() {

        /**
         * 结束工作
         */
        for (listener in listeners) {
            listener.stop(rootEntry?.getFile())
        }

    }

    /**
     * 比较已创建、修改或删除的文件的两个文件列表。
     *
     * @param parent 父条目
     * @param previous 原始文件列表 初始化文件状态
     * @param files  当前文件列表  更新是获取当前的文件状态
     */
    private fun checkAndNotify(parent: MyFile, previous: Array<MyFile>, files: Array<File>) {
        var c = 0
        val current: Array<MyFile> = if (files.isNotEmpty()) Array(files.size) {
            MyFile()
        } else MyFile.EMPTY_FILE_ENTRY_ARRAY
        for (entry in previous) {

            // 如果出现 与初始化时获取的文件夹中对应不上的文件名 说明 创建了新文件
            while (c < files.size && comparator!!.compare(entry.getFile(), files[c]) > 0) {
                current[c] = createFileEntry(parent, files[c])
                doCreate(current[c])
                c++
            }

            if (c < files.size && comparator!!.compare(entry.getFile(), files[c]) == 0) {
                doMatch(entry, files[c])
                checkAndNotify(entry, entry.getChildren(), listFiles(files[c]))
                current[c] = entry
                c++
            } else {
                checkAndNotify(entry, entry.getChildren(), FileUtil.EMPTY_FILE_ARRAY)
                doDelete(entry)
            }
        }
        while (c < files.size) {
            current[c] = createFileEntry(parent, files[c])
            doCreate(current[c])
            c++
        }
        parent.setChildren(current)
    }


    /**
     * 向注册的侦听器触发目录文件创建事件。
     *
     * @param entry 文件入口
     */
    private fun doCreate(entry: MyFile) {
        for (listener in listeners) {
            if (entry.isDirectory()) {
                listener.fileDirectoryCreate(entry.getFile())
            } else {
                listener.fileCreate(entry.getFile())
            }
        }
        val children: Array<MyFile> = entry.getChildren()
        for (aChildren in children) {
            doCreate(aChildren)
        }
    }

    /**
     * 向注册的侦听器触发目录文件更改事件。
     *
     * @param entry 以前的文件系统条目
     * @param file 以前的文件系统条目
     */
    private fun doMatch(entry: MyFile, file: File) {
        if (entry.refresh(file)) {
            for (listener in listeners) {
                if (entry.isDirectory()) {
                    listener.fileDirectoryChange(file)
                } else {
                    listener.fileChange(file)
                }
            }
        }

    }

    /**
     * 向注册的侦听器触发目录文件删除事件。
     *
     * @param entry 文件入口
     */
    private fun doDelete(entry: MyFile) {
        for (listener in listeners) {
            if (entry.isDirectory()) {
                listener.fileDirectoryDelete(entry.getFile())
            } else {
                listener.fileDelete(entry.getFile())
            }
        }

    }


    /**
     *列出文件
     * @param file 列出文件的文件
     * @param rootEntry 父条目
     * @return 子文件
     */
    private fun doListFiles(file: File?, rootEntry: MyFile): Array<MyFile> {
        val listFiles = listFiles(file!!)


        val children = if (listFiles.isNotEmpty()) Array(listFiles.size) {
            MyFile()
        } else MyFile.EMPTY_FILE_ENTRY_ARRAY
        for (value in listFiles.withIndex()) {
            children[value.index] = createFileEntry(rootEntry, listFiles[value.index])
        }

        return children
    }


    /**
     * Creates a new file entry for the specified file.
     *
     * @param parent The parent file entry
     * @param file The file to create an entry for
     * @return A new file entry
     */
    private fun createFileEntry(parent: MyFile, file: File): MyFile {
        val entry: MyFile = parent.newChildInstance(file)
        entry.refresh(file)
        val children: Array<MyFile> = doListFiles(file, entry)
        entry.setChildren(children)
        return entry
    }

    /**
     * 列出目录的内容
     *
     * @param file 列出内容的文件
     * @return 如果为空或文件不是目录，则为目录内容或零长度数组
     */
    private fun listFiles(file: File): Array<File> {
        var children: Array<File>? = null

        if (file.isDirectory) {
            children = if (fileFilter == null) file.listFiles() else file.listFiles(fileFilter)
        }

        if (children == null) {
            children = FileUtil.EMPTY_FILE_ARRAY
        }

        if (comparator != null && children.size > 1) {
            Arrays.sort(children, comparator)
        }

        return children
    }



}