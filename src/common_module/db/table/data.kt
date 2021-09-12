package com.example.common_module.db.table

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/5/20-18:21
 */
data class UserData(
        val name: String?,
        val userIcon: String?,
        val location: String?,
        val blogName: String?,
        val signature: String?,
        val email: String?,
        val password: String?,
        val repository:String?
)

data class BlogData(
        val title: String?,
        val href: String?,
        val year: String?,
        val month: String?,
        val day: String?,
)

data class LabelData(
        val title: String?,
        val tag: String?
)