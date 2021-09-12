package com.example

import com.example.common_module.utils.ConfUtil
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*


fun Application.module() {

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

    }
    conf(environment)

}

fun conf(environment: ApplicationEnvironment) {
    /**
     * 运行环境
     */
    ConfUtil.env = environment.config.property(ConfUtil.KEY_ENV).getString()


    /**
     * 打卡路径
     */
    ConfUtil.clockIn = environment.config.property( if(ConfUtil.env == "LOCAL") ConfUtil.CLOCKIN else ConfUtil.REMOTECLOCKIN).getString()

    /**
     *markdown的路径
     */
    ConfUtil.mdPath = environment.config.property( if(ConfUtil.env == "LOCAL") ConfUtil.KEY_LOCAL_MD else ConfUtil.KEY_REMOTE_MD).getString()



    ConfUtil.dbUrl =
        environment.config.property(if (ConfUtil.env == "LOCAL") ConfUtil.KEY_LOCAL_DB_URL else ConfUtil.KEY_REMOTE_DB_URL).getString()

    ConfUtil.dbDriver =
        environment.config.property(if (ConfUtil.env == "LOCAL") ConfUtil.KEY_LOCAL_DB_DRIVER else ConfUtil.KEY_REMOTE_DB_DRIVER).getString()

    ConfUtil.dbUser =
        environment.config.property( if (ConfUtil.env == "LOCAL") ConfUtil.KEY_LOCAL_DB_USER else ConfUtil.KEY_REMOTE_DB_USER).getString()

    ConfUtil.dbPWD =
        environment.config.property( if (ConfUtil.env == "LOCAL") ConfUtil.KEY_LOCAL_DB_PWD else ConfUtil.KEY_REMOTE_DB_PWD).getString()
}

