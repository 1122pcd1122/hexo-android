package activitytest.example.com.roomdemo.main.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BlogTags")
data class BlogTags (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    val id:Int = 0,

    @ColumnInfo(name = "tagName",typeAffinity = ColumnInfo.TEXT)
    val tagName:String?,

    @ColumnInfo(name = "blogId",typeAffinity =ColumnInfo.TEXT)
    val blogId:String?,

    @ColumnInfo(name = "blogTitle",typeAffinity = ColumnInfo.TEXT)
    val blogTitle:String?,

)