package activitytest.example.com.roomdemo.main.database

import activitytest.example.com.roomdemo.MyApplication
import activitytest.example.com.roomdemo.main.database.dao.BlogDao
import activitytest.example.com.roomdemo.main.database.dao.BlogTagsDao
import activitytest.example.com.roomdemo.main.database.dao.ConfigurationDao
import activitytest.example.com.roomdemo.main.database.dao.TagsDao
import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.main.database.entity.BlogTags
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 数据库
 */
@Database(entities = [Configuration::class, Blog::class, BlogTags::class, Tags::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract val configurationDao: ConfigurationDao
    abstract val blogDao:BlogDao
    abstract val tagsDao:TagsDao
    abstract val blogTagsDao:BlogTagsDao

    companion object {

        private const val DATABASE_NAME = "my_db"
        private var databaseInstance: MyDatabase? = null
        private val TAG = MyDatabase::class.java.name

        @get:Synchronized
        val instance: MyDatabase?
            get() {
                if (databaseInstance == null) {
                    databaseInstance = MyApplication.context?.let {
                        Room.databaseBuilder(it, MyDatabase::class.java, DATABASE_NAME)
                                .allowMainThreadQueries()
                                .build()
                    }
                }
                Log.d(TAG, "数据库建立")
                return databaseInstance
            }


    }

}