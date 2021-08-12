package com.example.fileListener_module.fileListener

import java.io.File
import java.nio.file.Files
import java.util.*

/**
 * @Description 文件工具类
 * @author 裴晨栋
 * @date 2021/6/25-13:20
 */
class FileUtil {
    companion object{

        /**
         * `File` 类型的空数组。
         */
        val EMPTY_FILE_ARRAY = arrayOf<File>()


        /**
         * 上一次的更改时间
         */
        fun lastModified(file: File):Long{
            return Files.getLastModifiedTime(Objects.requireNonNull(file.toPath(), "file")).toMillis()
        }
    }
}