package com.example.fileListener_module.fileListener

import java.io.File
import java.util.*
import kotlin.jvm.Throws

/**
 * @Description 文件比较
 * @author 裴晨栋
 * @date 2021/6/25-17:23
 */
class FileComparator:Comparator<File> {

    /**
     *使用区分大小写规则比较两个字符串。
     *
     * @param str1  要比较的第一个字符串，不为空
     * @param str2  要比较的第二个字符串，不为空
     * @param sensitive true 不忽略大小写 false 忽略大小写
     * @throws NullPointerException if either string is null
     */
    @Throws(NullPointerException::class)
    private fun checkCompareTo(str1: String, str2: String, sensitive: Boolean): Int {
        Objects.requireNonNull(str1, "str1")
        Objects.requireNonNull(str2, "str2")
        return if (sensitive) str1.compareTo(str2) else str1.compareTo(str2, ignoreCase = true)
    }

    override fun compare(o1: File?, o2: File?): Int {
      return  checkCompareTo(o1?.name.toString(), o2?.name.toString(),false)
    }
}