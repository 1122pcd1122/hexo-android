package activitytest.example.com.roomdemo.main.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * configuration的dao层
 */
@Dao
public interface ConfigurationDao {

    @Insert
    void insertConfiguration(Configuration[] configuration);

    @Query ( "SELECT * FROM CONFIGURATION" )
    LiveData<Configuration> queryConfiguration();

    @Update
    void updateConfiguration(Configuration[] configuration);
}
