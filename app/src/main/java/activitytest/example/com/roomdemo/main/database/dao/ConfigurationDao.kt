package activitytest.example.com.roomdemo.main.database.dao

import activitytest.example.com.roomdemo.main.database.entity.Configuration
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * configuration的dao层
 */
@Dao
interface ConfigurationDao {
    @Insert
    suspend fun insertConfiguration(configuration: Configuration)

    @Query("SELECT * FROM CONFIGURATION")
     fun queryConfiguration(): LiveData<Configuration?>?

    @Update
    suspend fun updateConfiguration(configuration: Configuration)

}