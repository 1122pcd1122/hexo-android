package com.example.common_module.db.dao

import common_module.db.DB
import common_module.db.table.UserTable
import com.example.common_module.db.table.UserData
import me.liuwj.ktorm.database.iterator
import me.liuwj.ktorm.dsl.*

class UserDao {
    companion object{

        /**
         * 查询用户信息
         */
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

        /**
         * 插入博客名
         */
        fun insertBlogName(title:String){
            DB.database.update(UserTable){
                set(it.blogName,title)

                where {
                    it.name eq "pcd"
                }
            }
        }

        fun querySignature():String?{
            val query = DB.database.from(UserTable).select().where {
                UserTable.name eq "pcd"
            }

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val user = iterator.next()
                return user[UserTable.signature]
            }

            return null
        }

        fun queryBlogName(): String? {
            val query = DB.database.from(UserTable).select().where {
                UserTable.name eq "pcd"
            }

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val user = iterator.next()
                return user[UserTable.blogName]
            }

            return null
        }

        /**
         *插入个性签名
         */
        fun insertSignature(signature: String?) {
            DB.database.update(UserTable){
                set(it.signature,signature)

                where {
                    it.name eq "pcd"
                }
            }
        }

    }

}

