package activitytest.example.com.roomdemo.main.database.dao

import activitytest.example.com.roomdemo.main.database.entity.Blog
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BlogDao {
    @Insert
    fun insertBlog(Blog: Blog?)

    @Query("SELECT * FROM Blog")
    fun queryBlog(): LiveData<Blog?>?

    @Query("SELECT * FROM Blog WHERE blogId = :blogId")
    fun queryByIdInBlog(blogId:String?):LiveData<Blog?>?

    @Update
    fun updateBlog(Blog: Array<out Blog?>)

    @Query("SELECT blogId FROM Blog")
    fun queryBlogId():LiveData<List<String>>

    @Query("SELECT COUNT(*) FROM Blog")
    fun queryBlogNum():Int
}