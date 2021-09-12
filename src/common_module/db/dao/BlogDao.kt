package com.example.common_module.db.dao

import common_module.db.DB
import common_module.db.table.BlogTable
import com.example.common_module.db.table.BlogData
import common_module.db.table.LabelTable
import me.liuwj.ktorm.database.iterator
import me.liuwj.ktorm.dsl.*

class BlogDao {
    companion object {
        /**
         *
         * 向数据库 blog表中添加一条博客信息
         *
         * @param article 博客信息
         */
        fun insertArticle(article: BlogData?) {
            DB.database.insert(BlogTable) {
                set(it.title, article?.title)
                set(it.href, article?.href)
                set(it.year, article?.year)
                set(it.month, article?.month)
                set(it.day, article?.day)
            }
        }


        /**
         * 判断文章是否存在
         * @param title 文章标题
         */
        fun isContain(title: String?): Boolean {
            if (title == null) {
                return false
            }
            val query = DB.database.from(BlogTable).select(BlogTable.title)
            val where = query.where { BlogTable.title eq title }
            //标题数
            return where.iterator().hasNext()
        }


        /**
         * 根据年份来获取文章
         */
        fun listArticleByYear(year: String?): List<BlogData?> {
            return listArticleByYearOrMonth(year)
        }


        /**
         * 获取文章的发布年份
         */
        fun listYears(): List<String?> {
            val query = DB.database.from(BlogTable).select(BlogTable.year)
            val yearList = mutableListOf<String?>()
            for (row in query.rowSet) {
                yearList.add(row[BlogTable.year])
            }
            return yearList.distinct()
        }

        /**
         * 获取文章的标题
         */
        fun listAllArticleTitle(): List<String?> {
            val query = DB.database.from(BlogTable).select(BlogTable.title)
            val titleList = mutableListOf<String?>()
            val iterator = query.iterator()
            while (iterator.hasNext()){
                val title = iterator.next()[BlogTable.title]
                titleList.add(title)
            }
            return titleList
        }

        /**
         * 获取所有文章
         */
        fun listAllArticle(): List<BlogData?> {
            val query = DB.database.from(BlogTable).select()
            val articleList = mutableListOf<BlogData?>()
            listArticle(query, articleList)

            return articleList
        }

        /**
         * 根据查询条件,获取文章列表
         */
        private fun listArticle(query: Query, articleList: MutableList<BlogData?>) {

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val next = iterator.next()
                val blogData = BlogData(next[BlogTable.title],
                    next[BlogTable.href],
                    next[BlogTable.year],
                    next[BlogTable.month],
                    next[BlogTable.day])
                articleList.add(blogData)
            }
        }




        /**
         * 根据年或月来获取文章
         */
        private fun listArticleByYearOrMonth(yearOrMonth: String?): List<BlogData?> {
            if (yearOrMonth == null) {
                return emptyList()
            }

            val query = DB.database.from(BlogTable).select()
            val where = query.where { BlogTable.year eq yearOrMonth }
            val blogList = mutableListOf<BlogData>()

            for (row in where.rowSet) {

                val blogData = BlogData(
                    row[BlogTable.title],
                    row[BlogTable.href],
                    row[BlogTable.year],
                    row[BlogTable.month],
                    row[BlogTable.day],
                )

                blogList.add(blogData)
            }

            return blogList
        }


        /**
         * 文章数
         */
        fun articleNum(): Int {
            val query = DB.database.from(BlogTable).select()
            return query.rowSet.size()
        }




        /*
        * 删除文章
        * */
        fun deleteArticle(title: String?) {
            DB.database.delete(BlogTable) {
                it.title eq title.toString()
            }
        }

        fun isChange(blogData: BlogData?): Boolean {
            val query = DB.database.from(BlogTable).select().where {
                BlogTable.title eq blogData?.title.toString()
            }

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val next = iterator.next()
                if (!blogData?.href.equals(next[BlogTable.href]) || !blogData?.year.equals(next[BlogTable.year])
                    || !blogData?.month.equals(next[BlogTable.month]) || !blogData?.day.equals(next[BlogTable.day])){
                    return true
                }
                break
            }

            return false
        }

        /**
         * 更细文章信息
         */
        fun updateArticle(blogData: BlogData?) {
            DB.database.update(BlogTable) {
                this.set(BlogTable.href,blogData?.href)
                this.set(BlogTable.year, blogData?.year)
                this.set(BlogTable.month, blogData?.month)
                this.set(BlogTable.day, blogData?.day)

                where {
                    it.title eq blogData?.title.toString()
                }
            }
        }


        fun listArticleByLabel(label:String?): List<String> {
            val query = DB.database.from(LabelTable).select().where {
                LabelTable.tag eq label.toString()
            }

            val titleList = mutableListOf<String>()

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val next = iterator.next()
                titleList.add(next[LabelTable.title].toString())
            }

            return titleList
        }

        fun queryArticleInfo(title:String?): BlogData? {
            val query = DB.database.from(BlogTable).select().where {
                BlogTable.title eq title.toString()
            }
            val iterator = query.iterator()
            var blogData:BlogData?=null
            while (iterator.hasNext()){
                val next = iterator.next()
                blogData = BlogData(
                    next[BlogTable.title],
                    next[BlogTable.href],
                    next[BlogTable.year],
                    next[BlogTable.month],
                    next[BlogTable.day],
                )
                break
            }
            return blogData
        }

    }
}

