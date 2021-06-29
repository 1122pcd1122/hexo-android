package com.example.label_module



import com.example.common_module.db.dao.LabelDao
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val logger: Logger = LoggerFactory.getLogger(LabelFunction::class.java.name)

/**
 * 标签数量
 */
fun Route.labelNum() {
    get("/labelNum") {
        val tagsNum = LabelDao.labelNum()
        val respond = ResponseInfo.TagsNum(200, tagsNum, "标签数量")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")
    }
}

/**
 * 获取所有标签
 */
fun Route.labels(){
    get("/labels"){
        val labels = LabelDao.labels()
        val respond = ResponseInfo.Tags(200, labels, "所有标签")
        call.respond(respond)
        logger.info("服务器响应成功 信息获取状态码:${respond.code} 信息状态描述:${respond.message}")

    }
}