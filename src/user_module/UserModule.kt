package com.example.user_module

import com.example.user_module.btnNight
import com.example.user_module.btnWhite
import com.example.user_module.userIcon
import com.example.user_module.userInfo
import io.ktor.application.*
import io.ktor.routing.*


fun Application.user() {
    routing {
        userInfo()
        userIcon()
        btnNight()
        btnWhite()
    }
}