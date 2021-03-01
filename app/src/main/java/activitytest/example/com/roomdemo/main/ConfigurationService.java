package activitytest.example.com.roomdemo.main;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import activitytest.example.com.roomdemo.MyApplication;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.database.ConfigurationDao;
import activitytest.example.com.roomdemo.main.database.MyDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class ConfigurationService extends LifecycleService {
    private final String TAG = ConfigurationService.class.getName ();

    public ConfigurationService() {

    }

    @Override
    public void onCreate() {
        Log.d ( TAG,"创建服务" );
        super.onCreate ();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d ( TAG,"开始服务......" );
        Timer timer = new Timer ();

        TimerTask timerTask = new TimerTask () {
            @Override
            public void run() {
                Log.d ( TAG,"开始任务" );
                MainRepository instance = MainRepository.getInstance ();
                LiveData<Configuration> configurationMutableLiveData = instance.getConfiguration ();
                Handler handler = new Handler( Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        configurationMutableLiveData.observe (ConfigurationService.this, new Observer<Configuration> () {
                            @Override
                            public void onChanged(Configuration configuration) {
                                if (configuration != null){
                                    Log.d ( TAG,configuration.getId () +"");
                                    Log.d ( TAG,configuration.getName () );
                                    Log.d ( TAG,configuration.getIntroduce () );
                                    Log.d ( TAG,configuration.getBlog_username () );
                                    Log.d ( TAG,configuration.getIcon_night () );
                                    Log.d ( TAG,configuration.getIcon_white () );
                                    Log.d ( TAG,configuration.getListHeadlines () );
                                    manageDatabase ( configuration );
                                }
                                configurationMutableLiveData.removeObservers ( ConfigurationService.this );
                            }
                        } );
                    }
                });
            }
        };

        timer.schedule ( timerTask,new Date (),1000 * 60 * 60 * 24);



        return super.onStartCommand ( intent, flags, startId );
    }

    /**
     * 将获取的数据更新到数据库
     * @param c Configuration对象
     */
    public void manageDatabase(Configuration c){

        ConfigurationDao configurationDao = MyApplication.getMyDatabase ().getConfigurationDao ();
        LiveData<Configuration> configurationLiveData = configurationDao.queryConfiguration ();

        configurationLiveData.observe ( this, new Observer<Configuration> () {
            @Override
            public void onChanged(Configuration configuration) {
                if (configuration != null){
                    Log.d ( TAG,"更新数据" );
                    new UpdateAsyncTask (configurationDao).doInBackground ( c );
                }else {
                    Log.d ( TAG,"插入数据" );
                    new InsertAsyncTask (configurationDao).doInBackground ( c );
                }

                Log.d ( TAG,"移除观察" );
                configurationLiveData.removeObservers ( ConfigurationService.this );
            }
        } );
    }

    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        super.onBind ( intent );
        return null;
    }

    @Override
    public void onDestroy() {
        stopSelf ();
        super.onDestroy ();
    }



    static class InsertAsyncTask extends AsyncTask<Configuration,Integer,Integer> {

        ConfigurationDao configurationDao;

        public InsertAsyncTask(ConfigurationDao configurationDao) {
            this.configurationDao = configurationDao;
        }

        @Override
        protected Integer doInBackground(Configuration... configurations) {
            configurationDao.insertConfiguration ( configurations );
            return null;
        }
    }


    static class UpdateAsyncTask extends AsyncTask<Configuration,Integer,Integer>{
        ConfigurationDao configurationDao;

        public UpdateAsyncTask(ConfigurationDao configurationDao) {
            this.configurationDao = configurationDao;
        }
        @Override
        protected Integer doInBackground(Configuration... configurations) {
            configurationDao.updateConfiguration ( configurations );
            return null;
        }
    }
}