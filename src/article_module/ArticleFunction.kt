package com.example.article_module

import common_module.IO.FilePath
import com.example.common_module.utils.ServerUtil
import org.jsoup.nodes.Document
import java.io.File

class ArticleFunction {
    companion object {

        /*
            获取所有的html的文件名
         */
        fun articleNameHtml(): Array<String> {

            val file: File = if (ServerUtil.isDeploy) {
                File(FilePath.SERVER_HTML_PATH)
            } else {
                File(FilePath.LOCAL_HTML_PATH)
            }

            val list = file.list()
            println(list!!.toString())
            return list
        }

        /*
            获取所有的md格式的文件名
         */
        private fun articleNameMd(): Array<String> {

            val file: File = if (ServerUtil.isDeploy) {
                File(FilePath.SERVER_MD_PATH)
            } else {
                File(FilePath.LOCAL_MD_PATH)
            }

            val list = file.list()
            println(list!!.toString())
            return list
        }

     
        /*
            解析标签
         */
        private fun parseTags(parse: Document): Array<String> {
            val tags = parse.getElementsByTag("code")
            val element = tags[0]
            val split = element.text().split("\n").toTypedArray()

            for (i in split.indices) {
                val split1 = split[i].split("-").toTypedArray()
                split[i] = split1[1]
            }
//            TagsDaoInterface().insertTag(split.toList())
            return split
        }


        /**
         * 解析发布时间
         */
        private fun parseUpDateTime(parse: Document): String {
            val h2 = parse.getElementsByTag("h2")
            if (h2.isEmpty()) {
                return ""
            }
            val timeElement = h2[0]
            val date = timeElement.text()
            return date.substring(5, date.length)
        }

        /**
         *解析标题
         */
        private fun parseTitle(parse: Document): String {
            val h1 = parse.getElementsByTag("p")
            val titleElement = h1[0]
            val title = titleElement.text()
            return title.substring(6, title.length - 6)
        }
    }
}