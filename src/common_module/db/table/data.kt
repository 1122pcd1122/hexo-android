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
        val introduce: String?,
        val selfExperience: String?
)

data class BlogData(
        val title: String?,
        val date: String?,
        val tags: String?,
        val htmlUrl: String?,
        val year: Int?,
        val month: Int?,
        val day: Int?,
        val length:Int?
)

data class LabelData(
        val tagId: Int?,
        val tagName: String?
)