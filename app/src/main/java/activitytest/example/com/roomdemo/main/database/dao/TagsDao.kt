package activitytest.example.com.roomdemo.main.database.dao

import activitytest.example.com.roomdemo.main.database.entity.Tags
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TagsDao {
    @Insert
    suspend fun insertTags(Tags: Tags)

    @Query("SELECT * FROM tags")
     fun queryTags(): LiveData<Tags?>?

    @Query("SELECT * FROM tags WHERE tagName = :tagName")
     fun queryByIdInTags(tagName:String?): LiveData<Tags?>?

    @Update
    suspend fun updateTags(Tags: Array<out Tags>?)

    @Query("select tagName from tags")
     fun queryTagName():LiveData<List<String>?>?

    @Query("Select count(*)  from tags")
     fun queryTagNum():Int
}