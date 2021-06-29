package com.example.user_module

import com.example.common_module.db.table.UserData

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/5/25-13:45
 */
class ResponseInfo {
    data class UserInfo(val code: Int?, val userData: UserData?, val message: String?)
}