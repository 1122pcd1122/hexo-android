package com.example.user_module


import com.example.common_module.db.dao.UserDao
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val logger: Logger = LoggerFactory.getLogger(UserFunction::class.java.name)

/**
 * 用户所有信息
 */
fun Route.userInfo() {
    get("/userInfo") {
        val queryConfiguration = UserDao.queryConfiguration("pcd")
        val respond = ResponseInfo.UserInfo(200, queryConfiguration, "用户信息")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

/**
 * 用户icon
 */
fun Route.userIcon() {
    get("/userIcon"){
        val queryBlogIcon = UserDao.queryBlogIcon()
        val respond = ResponseInfo.UserIcon(200, queryBlogIcon, "用户头像")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

/**
 *
 */
fun Route.btnNight() {
    static("/btn_night") {

    }
}

/**
 *
 */
fun Route.btnWhite() {
    static("/btn_white") {

    }
}


