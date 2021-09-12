package com.example.user_module

import com.example.common_module.db.table.UserData

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/5/25-13:45
 */
class ResponseInfo {
    data class UserInfo(val code: Int?, val info: UserData?, val message: String?)

    data class ClockIn(val mobile:String?, val temperature:String?, val city:String?, val district:String?, val address:String?)

    data class ClockResponse(val code: String?,val msg: String?)

    data class UserIcon(val code: Int, val info: String?, val message: String?)
}