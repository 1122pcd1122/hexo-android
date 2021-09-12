package com.example.article_module

import io.ktor.application.*
import io.ktor.routing.*

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/5/20-18:56
 */
fun Application.article() {


    routing {

        articleByYear()
        articleTitleList()
        articleNum()
        article()
        articleByLabel()
    }


}