package activitytest.example.com.roomdemo.main.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tags")
data class Tags (
        /*
        标签名称
        */
        @PrimaryKey
        @ColumnInfo(name = "tagName",typeAffinity = ColumnInfo.TEXT)
        val tagName:String,
        /*
        标签内容
        */
        @ColumnInfo(name = "tagContent",typeAffinity = ColumnInfo.TEXT)
        val tagContent:String?
)