package activitytest.example.com.roomdemo.main.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 配置文件表
 */
@Entity(tableName = "Configuration")
data class Configuration (
    @PrimaryKey
    val id: Int = 0,

    /**
     * 博客名
     */
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    val name: String?,

    /**
     * 暗色图标
     */
    @ColumnInfo(name = "icon_night", typeAffinity = ColumnInfo.TEXT)
    val icon_night: String?,

    /**
     * 亮色图标
     */
    @ColumnInfo(name = "icon_white", typeAffinity = ColumnInfo.TEXT)
    val icon_white: String?,

    /**
     * 博主名字
     */
    @ColumnInfo(name = "blog_username", typeAffinity = ColumnInfo.TEXT)
    val blog_username: String?,

    /**
     * 博主介绍
     */
    @ColumnInfo(name = "introduce", typeAffinity = ColumnInfo.TEXT)
    val introduce: String?,

    /**
     * 列表标题
     */
    @ColumnInfo(name = "listHeadlines", typeAffinity = ColumnInfo.TEXT)
    val listHeadlines: String?

)