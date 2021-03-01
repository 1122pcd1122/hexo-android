package activitytest.example.com.roomdemo.main.database;


import android.util.Log;

import activitytest.example.com.roomdemo.MyApplication;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/**
 * 数据库
 */
@Database ( entities = {Configuration.class} ,version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_db";

    private static MyDatabase databaseInstance;
    private static final String TAG = MyDatabase.class.getName ();

    public static synchronized MyDatabase getInstance(){
        if (databaseInstance == null){
            databaseInstance = Room
                    .databaseBuilder ( MyApplication.getContext (),MyDatabase.class,DATABASE_NAME )
                    .allowMainThreadQueries ()
                    .build ();
        }

        Log.d ( TAG,"数据库建立" );
        return databaseInstance;
    }

    public abstract ConfigurationDao getConfigurationDao();
}
