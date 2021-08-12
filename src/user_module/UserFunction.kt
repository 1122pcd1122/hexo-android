package com.example.user_module

import com.example.common_module.utils.ConfUtil
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader

class UserFunction {
    companion object{
        //获取文件内容

        fun clockIn(): List<String> {
            val list = mutableListOf<String>()
            val file = File(ConfUtil.clockIn.toString())
            val bufferedReader = BufferedReader(FileReader(file))
            bufferedReader.lines().forEach {
                list.add(it)
            }

            return list
        }

        fun pNums(): Int {
            val file = File("resources/clockIn.txt")
            val bufferedReader = BufferedReader(FileReader(file))
            return bufferedReader.lines().count().toInt()
        }


    }

}