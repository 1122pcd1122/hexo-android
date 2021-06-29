package com.example.user_module


import com.example.common_module.db.dao.UserDao
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val logger: Logger = LoggerFactory.getLogger(UserFunction::class.java.name)

fun Route.userInfo() {
    get("/userInfo") {
        val queryConfiguration = UserDao.queryConfiguration("pcd")
        val respond = ResponseInfo.UserInfo(200, queryConfiguration, "用户信息")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

fun Route.userIcon() {
    static("/userIcon") {
        file("icon", "C:\\Users\\peichendong\\Desktop\\blog\\images\\user_Icon.png")
    }
}

fun Route.btnNight() {
    static("/btn_night") {
        file("night", "C:\\Users\\peichendong\\Desktop\\blog\\images\\git_night.png")
    }
}

fun Route.btnWhite() {
    static("/btn_white") {
        file("white", "C:\\Users\\peichendong\\Desktop\\blog\\images\\git_white.png")
    }
}




