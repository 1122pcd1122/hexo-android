package com.example.common_module.db.dao

import com.example.common_module.db.table.LabelData
import com.example.user_module.logger
import common_module.db.DB
import common_module.db.table.LabelTable
import me.liuwj.ktorm.dsl.*

class LabelDao {
    companion object {

        /**
         * 插入标签
         */
        fun insertTag(labelData: LabelData?): Boolean {

            try {
                val db = DB.database.from(LabelTable)
                db.database.insert(LabelTable) {
                    set(it.title, labelData?.title)
                    set(it.tag, labelData?.tag)
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
            val query = DB.database.from(LabelTable).selectDistinct(LabelTable.tag)
            var len = 0
            val iterator = query.iterator()
            while (iterator.hasNext()){
                iterator.next()
                len++
            }
            return len
        }

        /**
         * 获取所有标签
         */
        fun labels(): List<String> {
            val list = mutableListOf<String>()
            val query = DB.database.from(LabelTable).selectDistinct(LabelTable.tag)

            val iterator = query.iterator()
            while (iterator.hasNext()){
                val tag = iterator.next()[LabelTable.tag]
                if (tag != null) {
                    list.add(tag)
                }
            }

            return list
        }

        /**
         * 查询标签是否存在
         */
        fun isContain(label: String?): Boolean {
            val query = DB.database.from(LabelTable).select().where {
                LabelTable.title eq label.toString()
            }

            return query.iterator().hasNext()
        }

        fun deleteTag(labelData: LabelData?){
            DB.database.delete(LabelTable){
                it.title eq labelData?.title.toString()
            }
        }


        fun updateTag(labelData: LabelData?){
            DB.database.update(LabelTable){
                it.tag eq  labelData?.tag.toString()
                where {
                    it.title eq labelData?.title.toString()
                }
            }
        }
    }

}

