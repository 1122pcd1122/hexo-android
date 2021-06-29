package com.example.fileListener_module

import java.io.File
import java.io.IOException

/**
 * @Description 文件实例
 * @author 裴晨栋
 * @date 2021/6/24-21:49
 */
class MyFile(){

    companion object{

        /**
         * 空的MyFile数组
         */
        val EMPTY_FILE_ENTRY_ARRAY: Array<MyFile> = arrayOf<MyFile>()
    }

    /**
     * 父亲
     */
    private var parent: MyFile? = null

    /**
     * 孩子
     */
    private var children: Array<MyFile>? = getChildren()

    /**
     * 当前MyFile对应的文件
     */
    private var file: File? = null

    /**
     * 文件名
     */
    private var name: String? = null

    /**
     * 文件是否存在
     */
    private var exists = false

    /**
     * 是否是文件夹
     */
    private var directory = false

    /**
     * 上一次的更新时间 单位:毫秒
     */
    private var lastModified: Long = 0

    /**
     * 文件长度
     */
    private var length: Long = 0


    /**
     * 构造器 文件对象来构造MyFile
     * @param file 构造MyFile对象的file对象
     */
    constructor(file: File?) : this(null, file)

    /**
     * 构造器 使用父亲MyFile和自身file构造MyFile
     */
    constructor(myFile: MyFile?, file: File?):this(){
        if (file == null){
            throw IllegalArgumentException("File is missing")
        }

        this.parent = myFile
        this.file = file
        this.name = file.name

    }

    /**
     * 刷新文件属性 并指示是否更改
     */
    fun refresh(file: File?):Boolean{

        //缓存上一次修改/变化的文件/文件夹属性
        val origExist = exists
        val origLastModified = lastModified
        val origDirectory = directory
        val origLength = length

        name = file?.name
        exists =file?.exists()!!

        directory = exists && file.isDirectory

        lastModified = try {

            if (exists){
                FileUtil.lastModified(file)
            }else{
                0
            }
        }catch (e: IOException){
            0
        }

        length = if (exists && !directory){
            file.length()
        }else{
            0
        }

        //返回是否有文件变化
        return exists != origExist || lastModified != origLastModified || directory != origDirectory || length != origLength
    }

    /**
     * 创建一个子类实例
     *
     * @param file 子类file
     * @return 一个子类实例
     */
     fun newChildInstance(file: File?): MyFile {
        return MyFile(this, file)
    }

    /**
     * 返回父类的myFile
     *
     * @return 父类MyFile
     */
    fun getParent(): MyFile?{
        return parent
    }


    /**
     *返回上次检查的最后修改时间。
     *
     * @return 最后修改时间
     */
    fun getLastModified(): Long {
        return lastModified
    }

    /**
     * 返回上次检查的最后修改时间。
     *
     * @param lastModified 最后修改时间
     */
    fun setLastModified(lastModified: Long) {
        this.lastModified = lastModified
    }

    /**
     * 返回水平
     *
     * @return 当前水平
     */
    fun getLevel(): Int {
        return if (parent == null) 0 else parent!!.getLevel() + 1
    }

    /**
     * 返回目录的文件。
     *
     * @return 如果文件不是目录或目录为空，则此目录的文件或空数组
     */
    fun getChildren():Array<MyFile>{
        return children ?: EMPTY_FILE_ENTRY_ARRAY
    }


    /**
     * 设置目录的文件。
     *
     * @param children 此目录的文件，可能为空
     */
    fun setChildren(children: Array<MyFile>){
        this.children = children
    }

    /**
     * 返回被监控的文件。
     *
     * @return 被监控的文件
     */
    fun getFile(): File? {
        return file
    }

    /**
     * 返回文件名。
     *
     * @return 文件名
     */
    fun getName(): String? {
        return name
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    fun setName(name: String){
        this.name = name
    }

    /**
     * 返回长度
     *
     * @return 长度
     */
    fun getLength():Long{
        return length
    }

    /**
     * 设置长度
     *
     * @param length 长度
     */
    fun setLength(length: Long){
        this.length = length
    }

    /**
     *指示上次检查文件是否存在。
     *
     * @return 文件是否存在
     */
    fun isExists():Boolean{
        return exists
    }

    /**
     * 设置上次检查文件是否存在。
     *
     * @param exists 文件是否存在
     */
    fun setExists(exists: Boolean){
        this.exists = exists
    }

    /**
     * 指示文件是否为目录。
     *
     * @return 文件是否为目录
     */
    fun isDirectory(): Boolean {
        return directory
    }

    /**
     * 设置文件是否为目录。
     *
     * @param directory 文件是否为目录
     */
    fun setDirectory(directory: Boolean) {
        this.directory = directory
    }
}
