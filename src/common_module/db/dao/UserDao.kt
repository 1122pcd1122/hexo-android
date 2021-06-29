package com.example.common_module.db.dao

import common_module.db.DB
import common_module.db.table.UserTable
import com.example.common_module.db.table.UserData
import me.liuwj.ktorm.database.iterator
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.dsl.from
import me.liuwj.ktorm.dsl.select
import me.liuwj.ktorm.dsl.where

class UserDao {
    companion object{
        fun queryConfiguration(name: String): UserData? {
            val query = DB.database.from(UserTable).select().where {
                UserTable.name eq name
            }

            val rowSet = query.rowSet

            for (row in rowSet) {
                return UserData(
                    row[UserTable.name],
                    row[UserTable.userIcon],
                    row[UserTable.location],
                    row[UserTable.blogName],
                    row[UserTable.signature],
                    row[UserTable.introduce],
                    row[UserTable.selfExperience]
                )
            }

            return null
        }

    }

}

