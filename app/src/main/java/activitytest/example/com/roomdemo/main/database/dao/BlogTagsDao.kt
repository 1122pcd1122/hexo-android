package activitytest.example.com.roomdemo.main.database.dao

import activitytest.example.com.roomdemo.main.database.entity.BlogTags
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BlogTagsDao {
    @Insert
    fun insertTags(BlogTags: Array<out BlogTags?>)

    @Query("SELECT * FROM BlogTags")
    fun queryTags(): LiveData<BlogTags?>?

    @Query("SELECT * FROM BlogTags WHERE tagName = :tagName")
    fun queryByIdInTags(tagName:String?): LiveData<BlogTags?>?

    @Update
    fun updateTags(BlogTags: Array<out BlogTags?>)
}