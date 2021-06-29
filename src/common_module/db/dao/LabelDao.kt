package com.example.common_module.db.dao

import common_module.db.DB
import common_module.db.table.LabelTable
import me.liuwj.ktorm.dsl.*

class LabelDao {
    companion object{

        fun insertTag(tag: String?): Boolean {

            if (tag == null || tag == "") {
                return false
            }

            if (isContain(tag)) {
                val db = DB.database.from(LabelTable)
                db.database.insert(LabelTable) {
                    set(it.tagName, tag)
                }

                return true
            }

            return false
        }


        fun labelNum(): Int {
            val query = DB.database.from(LabelTable).select()
            return query.rowSet.size()
        }

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

        fun isContain(label: String?): Boolean {
            val query = DB.database.from(LabelTable).select().where {
                LabelTable.tagName eq label!!
            }

            return query.rowSet.size() == 0
        }





    }
}

