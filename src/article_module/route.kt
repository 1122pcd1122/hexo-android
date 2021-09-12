package com.example.article_module

import com.example.common_module.db.dao.BlogDao
import com.example.common_module.db.table.BlogData
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger(ArticleFunction::class.java.name)



/**
 * 获取所有文章名
 */
fun Route.articleTitleList() {
    get("/listTitle") {
        val listArticleTitle = BlogDao.listAllArticleTitle()
        val respond = Status.ListArticleTitle(200, listArticleTitle, "文章标题")
        //假定获取成功...
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

/**
 * 获取所有文章
 */
fun Route.article() {

    get("/article") {
        val listAllArticle = BlogDao.listAllArticle()
        val respond = Status.ListArticle(200, listAllArticle, "所有文章")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}


/**
 *根据年份获取文章信息
 */
fun Route.articleByYear() {

    get("/articleByYear") {
        //响应体
        val respond: Status.AllListArticleByYear

        //响应逻
        val listof = mutableListOf<Status.ListArticleByYear>()
        BlogDao.listYears().forEach {
            val listArticleByYear = BlogDao.listArticleByYear(it)
            val articleByYear = Status.ListArticleByYear(it!!, listArticleByYear,"")
            listof.add(articleByYear)
        }


        respond = Status.AllListArticleByYear(200, listof, "日志")
        call.respond(respond)



        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")

    }
}


/**
 *     文章数
 */
fun Route.articleNum() {

    get("/articleNum") {
        val articleNum = BlogDao.articleNum()
        val respond = Status.ArticleNum(200, articleNum, "文章数")
        call.respond(respond)
        logger.info("服务器响应成功 信息状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

fun Route.articleByLabel(){
    get("/articles") {
        val receiveParameters = call.request.queryParameters
        val label = receiveParameters["label"]
        val listArticleByLabel = BlogDao.listArticleByLabel(label)
        val mutableListOf = mutableListOf<BlogData>()
        listArticleByLabel.forEach {
            val queryArticleInfo = BlogDao.queryArticleInfo(it)
            if (queryArticleInfo != null) {
                mutableListOf.add(queryArticleInfo)
            }
        }

        val respond = Status.ArticlesByLabel(200, mutableListOf, "文章")
        call.respond(respond)
        logger.info("服务器响应成功 信息状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

