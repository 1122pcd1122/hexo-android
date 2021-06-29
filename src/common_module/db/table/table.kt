package common_module.db.table

import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.varchar

/**
 * 用户表
 */
sealed class User(tableName: String) : Table<Nothing>(tableName) {
    val name = varchar("name").primaryKey()
    val userIcon = varchar("user_icon")
    val location = varchar("location")
    val blogName = varchar("blog_name")
    val signature = varchar("signature")
    val introduce = text("introduce")
    val selfExperience = text("self_experience")
}

object UserTable : User("user")

/**
 * 博客文章
 */
sealed class Blog (tableName: String) : Table<Nothing>(tableName) {
    val title = varchar("title").primaryKey()
    val date = text("date")
    val tags = text("tags")
    val htmlUrl = text("htmlUrl")
    val year = int("year")
    val month = int("month")
    val day = int("day")
    val length = int("length")
}

object BlogTable : Blog("blog")

/**
 * 标签
 */
sealed class Label(tableName: String) : Table<Nothing>(tableName) {
    val tagId = int("tagId").primaryKey()
    val tagName = varchar("tagName")
}

object LabelTable : Label("label")
