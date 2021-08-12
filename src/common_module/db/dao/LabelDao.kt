package com.example.common_module.db.dao

import com.example.user_module.logger
import common_module.db.DB
import common_module.db.table.LabelTable
import me.liuwj.ktorm.dsl.*

class LabelDao {
    companion object {

        /**
         * 插入标签
         */
        fun insertTag(tag: String?): Boolean {

            if (tag == null || tag == "") {
                return false
            }

            try {
                val db = DB.database.from(LabelTable)
                db.database.insert(LabelTable) {
                    set(it.tagName, tag)
                }
            }catch (e:Exception){
                logger.info(e.toString())
                return false
            }

            return true

        }


        /**
         * 标签数量
         */
        fun labelNum(): Int {
            val query = DB.database.from(LabelTable).select()
            return query.rowSet.size()
        }

        /**
         * 获取所有标签
         */
        fun labels(): List<String> {
            val list = mutableListOf<String>()
            val query = DB.database.from(LabelTable).select()
            query.forEach {
                val string = it.getString("tagName")
                if (string != null) {
                    list.add(string)
                }
            }
            return list
        }

        /**
         * 查询标签是否存在
         */
        fun isContain(label: String?): Boolean {
            val query = DB.database.from(LabelTable).select().where {
                LabelTable.tagName eq label!!
            }

            if (query.rowSet.size() == 0){
                return false
            }
            return true
        }


        /**
         * 删除标签
         */
        fun deleteLabel(tag: String?) {
            DB.database.delete(LabelTable) {
                it.tagName.eq(tag.toString())
            }
        }


    }
}

