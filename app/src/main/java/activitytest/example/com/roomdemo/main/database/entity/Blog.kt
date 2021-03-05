package activitytest.example.com.roomdemo.main.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blog",primaryKeys = ["blogId","blogTitle"])
data class Blog (
    /*
        博客id
     */
    @ColumnInfo(name = "blogId", typeAffinity = ColumnInfo.TEXT)
    val blogId:String,

    /**
     * 标题
     */
    @ColumnInfo(name = "blogTitle",typeAffinity = ColumnInfo.TEXT)
    val blogTitle: String,

    /**
     * 时间
     */
    @ColumnInfo(name = "updateTime",typeAffinity = ColumnInfo.TEXT)
    val updateTime: String?,

    /**
     * html字符串
     */
    @ColumnInfo(name = "html",typeAffinity = ColumnInfo.TEXT)
    val html: String?


)