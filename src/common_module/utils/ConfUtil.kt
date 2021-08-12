package com.example.common_module.utils


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.IllegalStateException

/**
 * @Description conf的配置工具类
 * @author 裴晨栋
 * @date 2021/6/26-16:52
 */
class ConfUtil {
    companion object {

        private var logger: Logger = LoggerFactory.getLogger(ConfUtil::class.java.name)

        /**
         *  LOCAL 本地运行环境
         *  ONLINE 上线环境
         *  PRE 预发环境
         */
        var env: String? = null
            set(value) {
                logger.info("运行环境为: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null){
                    throw IllegalStateException("运行环境 未配置")
                }
                return field.toString()
            }

        var clockIn:String? = null
            set(value) {
                logger.info("打卡文件路径为: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null){
                    throw IllegalStateException("打卡文件 未配置")
                }
                return field.toString()
            }

        var mdPath: String? = null
            set(value) {
                logger.info("设置 markdown 文件路径为: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null) {
                    throw IllegalStateException("markdown 未配置")
                }
                return field.toString()
            }


        var dbUrl: String? = null
            set(value) {
                logger.info("数据库的url: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null) {
                    throw IllegalStateException("数据库的url 未配置")
                }
                return field.toString()
            }


        var dbDriver: String? = null
            set(value) {
                logger.info("数据库的引擎: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null) {
                    throw IllegalStateException("数据的引擎 未配置")
                }
                return field.toString()
            }

        var dbUser: String? = null
            set(value) {
                logger.info("数据库的用户名: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null) {
                    throw IllegalStateException("数据的用户名 未配置")
                }
                return field.toString()
            }

        var dbPWD: String? = null
            set(value) {
                logger.info("本地数据库的密码: ${value.toString()}")
                field = value
            }
            get() {
                if (field == null) {
                    throw IllegalStateException("本地数据的密码 未配置")
                }
                return field.toString()
            }




        const val KEY_ENV = "ktor.security.env"

        /**
         * 本地数据库配置
         */
        const val KEY_LOCAL_DB_URL = "ktor.security.localDb.url"
        const val KEY_LOCAL_DB_DRIVER = "ktor.security.localDb.driver"
        const val KEY_LOCAL_DB_USER = "ktor.security.localDb.user"
        const val KEY_LOCAL_DB_PWD = "ktor.security.localDb.password"

        /**
         * 远程数据库配置
         */
        const val KEY_REMOTE_DB_URL = "ktor.security.remoteDb.url"
        const val KEY_REMOTE_DB_DRIVER = "ktor.security.remoteDb.driver"
        const val KEY_REMOTE_DB_USER = "ktor.security.remoteDb.user"
        const val KEY_REMOTE_DB_PWD = "ktor.security.remoteDb.password"
        /**
         * 本地md
         */
        const val KEY_LOCAL_MD = "ktor.security.md"

        /**
         * 远程md
         */
        const val KEY_REMOTE_MD = "ktor.security.remoteMd"

        /**
         * 远程打卡和本地打卡
         */
        const val CLOCKIN = "ktor.security.clockIn"
        const val REMOTECLOCKIN = "ktor.security.remoteClockIn"



    }


}