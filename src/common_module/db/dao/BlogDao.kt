package com.example.common_module.db.dao

import common_module.db.DB
import common_module.db.table.BlogTable
import com.example.common_module.db.table.BlogData
import me.liuwj.ktorm.database.iterator
import me.liuwj.ktorm.dsl.*

class BlogDao {
    companion object{
        /**
         *
         * 向数据库 blog表中添加一条博客信息
         *
         * @param article 博客信息
         */
        fun insertArticleInfo(article: BlogData?) {
            DB.database.insert(BlogTable) {
                set(it.title, article?.title)
                set(it.date, article?.date)
                set(it.tags, article?.tags)
                set(it.htmlUrl, article?.htmlUrl)
                set(it.year, article?.year)
                set(it.month, article?.month)
                set(it.day, article?.day)
                set(it.length,article?.length)
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
            val size = where.rowSet.size()
            return size > 0
        }


        fun listArticleByYear(year: Int?): List<BlogData?> {

            return listArticleByYearOrMonth(year)
        }


        fun listYears(): List<Int?> {
            val query = DB.database.from(BlogTable).select(BlogTable.year)
            val yearList = mutableListOf<Int?>()
            for (row in query.rowSet) {
                yearList.add(row[BlogTable.year])
            }
            return yearList.distinct()
        }

        fun listAllArticleTitle(): List<String?> {
            val query = DB.database.from(BlogTable).select(BlogTable.title)
            val titleList = mutableListOf<String?>()
            for (row in query.rowSet) {
                titleList.add(row[BlogTable.title])
            }
            return titleList
        }

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
            for (row in query.rowSet) {
                val blogData = BlogData(
                        row[BlogTable.title],
                        row[BlogTable.date],
                        row[BlogTable.tags],
                        row[BlogTable.htmlUrl],
                        row[BlogTable.year],
                        row[BlogTable.month],
                        row[BlogTable.day],
                    row[BlogTable.length])

                articleList.add(blogData)
            }
        }


        fun listBlogByMonth(month: Int?): List<BlogData?> {
            return listArticleByYearOrMonth(month)
        }

        /**
         * 根据年或月来获取文章
         */
        private fun listArticleByYearOrMonth(yearOrMonth: Int?): List<BlogData?> {
            if (yearOrMonth == null) {
                return emptyList()
            }

            val query = DB.database.from(BlogTable).select()
            val where = query.where { BlogTable.year eq yearOrMonth }
            val blogList = mutableListOf<BlogData>()

            for (row in where.rowSet) {

                val blogData = BlogData(
                        row[BlogTable.title],
                        row[BlogTable.date],
                        row[BlogTable.tags],
                        row[BlogTable.htmlUrl],
                        row[BlogTable.year],
                        row[BlogTable.month],
                        row[BlogTable.day],
                        row[BlogTable.length])

                blogList.add(blogData)
            }

            return blogList
        }


        fun articleNum(): Int {
            val query = DB.database.from(BlogTable).select()
            return query.rowSet.size()
        }

        fun articlesByLabel(label: String): List<BlogData> {
            val query = DB.database.from(BlogTable).select().where {
                BlogTable.tags like "%${label}%"
            }

            val list = mutableListOf<BlogData>()
            for (row in query.rowSet) {

                val blogData = BlogData(
                        row[BlogTable.title],
                        row[BlogTable.date],
                        row[BlogTable.tags],
                        row[BlogTable.htmlUrl],
                        row[BlogTable.year],
                        row[BlogTable.month],
                        row[BlogTable.day],
                        row[BlogTable.length])

                list.add(blogData)
            }

            return list
        }


        /*
        * 删除文章
        * */
        fun deleteArticle(title: String?){
            DB.database.delete(BlogTable){
                 it.title.eq(title.toString())
            }
        }

        /**
         * 更细文章信息
         */
        fun updateArticle(blogData: BlogData?){
            DB.database.update(BlogTable){
                this.set(BlogTable.date,blogData?.date)
                this.set(BlogTable.year,blogData?.year)
                this.set(BlogTable.tags,blogData?.tags)
                this.set(BlogTable.month,blogData?.month)
                this.set(BlogTable.day,blogData?.day)
                this.set(BlogTable.length,blogData?.length)
            }
        }
    }
}

