package com.example.article_module

import com.example.common_module.db.table.BlogData

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/5/20-19:19
 */
sealed class Status {
    //文章标题
    data class ListArticleTitle(val code: Int?, val listTitle: List<String?>?, val message: String?)

    //所有文章
    data class ListArticle(val code: Int?, val listArticle: List<BlogData?>?, val message: String?)

    //文章数
    data class ArticleNum(val code: Int?, val articleNum: Int?, val message: String?)

    //日志
    data class ListArticleByYear(val year:Int,val listArticle: List<BlogData?>?)

    data class AllListArticleByYear(val code: Int?, val listArticleByYears: List<ListArticleByYear>?, val message: String?)


    data class ArticlesByLabel(val code: Int?, val listArticle: List<BlogData?>?, val message: String?)
}