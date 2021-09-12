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
    val email = text("email")
    val password = text("password")
    val repository = text("repository")
}

object UserTable : User("user")

/**
 * 博客文章
 */
sealed class Blog (tableName: String) : Table<Nothing>(tableName) {
    val title = varchar("title").primaryKey()
    val href = text("href")
    val year = text("year")
    val month = text("month")
    val day = text("day")
}

object BlogTable : Blog("blog")

/**
 * 标签
 */
sealed class Label(tableName: String) : Table<Nothing>(tableName) {
    val title = varchar("title").primaryKey()
    val tag = varchar("tag")
}

object LabelTable : Label("label")
