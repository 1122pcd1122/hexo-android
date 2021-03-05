package activitytest.example.com.roomdemo.main.database.dao

import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.nfc.Tag
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TagsDao {
    @Insert
    fun insertTags(Tags: Array<out Tags?>)

    @Query("SELECT * FROM tags")
    fun queryTags(): LiveData<Tags?>?

    @Query("SELECT * FROM tags WHERE tagName = :tagName")
    fun queryByIdInTags(tagName:String?): LiveData<Tags?>?

    @Update
    fun updateTags(Tags: Array<out Tags>?)
}