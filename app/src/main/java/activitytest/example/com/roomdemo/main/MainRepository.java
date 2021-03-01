package activitytest.example.com.roomdemo.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Objects;

import activitytest.example.com.roomdemo.MyApplication;
import activitytest.example.com.roomdemo.main.http.API;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.database.ConfigurationDao;
import activitytest.example.com.roomdemo.main.database.MyDatabase;
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private static MainRepository mainRepository;
    private final API API;
    private final String TAG = MainRepository.class.getName ();
    private final ConfigurationDao configurationDao;
    private final MutableLiveData<Integer> errorCodeLiveData;
    private final MutableLiveData<Configuration> configurationMutableLiveData;

    /**
     * 获取主仓库实例
     * @return mainRepository对象
     */
    public static MainRepository getInstance() {
        if (mainRepository == null){
            mainRepository = new MainRepository ();
        }
        return mainRepository;
    }

    /**
     * 主仓库构造方法
     * 获取数据库对象及Configuration的Dao层
     */
    public MainRepository() {
        API = MyApplication.getApi ();
        MyDatabase myDatabase = MyApplication.getMyDatabase ();
        configurationDao = myDatabase.getConfigurationDao ();
        errorCodeLiveData = new MutableLiveData<> ();
        configurationMutableLiveData = new MutableLiveData<> ();
    }

    /**
     * 通过Retrofit获取配置文件
     * @return Configuration对象实例
     */
    public LiveData<Configuration> getConfiguration(){
        Call<Configuration> configuration = API.getConfiguration ( "https://raw.githubusercontent.com/1122pcd1122/MyNotes/master/configuration.json" );
        configuration.enqueue ( new Callback<Configuration> () {
            @Override
            public void onResponse(Call<Configuration> call, Response<Configuration> response) {
                Log.d ( TAG,"网络连接成功" );
                Log.d ( TAG,response.code ()+"");
                if (response.code () == ErrorCodeEnum.SUCCESS.getErrorCode ()){
                    Log.d ( TAG,response.body ().toString () );
                    errorCodeLiveData.postValue ( ErrorCodeEnum.SUCCESS.getErrorCode () );
                    configurationMutableLiveData.postValue ( response.body () );
                }else {
                    errorCodeLiveData.postValue ( ErrorCodeEnum.ERROR_API.getErrorCode () );
                    configurationMutableLiveData.postValue ( null );
                }
            }

            @Override
            public void onFailure(Call<Configuration> call, Throwable t) {
                Log.d ( TAG,"网络连接失败" );
                Log.d ( TAG, Objects.requireNonNull ( t.getMessage () ) );
                errorCodeLiveData.postValue ( 400 );
                configurationMutableLiveData.postValue ( null );
            }
        } );

        return configurationMutableLiveData;
    }

    /**
     * 从数据库中查询的任务线程
     */
    @SuppressLint("StaticFieldLeak")
    class ConfigurationAsyncTask extends AsyncTask<Configuration,Integer,LiveData<Configuration>>{

        @Override
        protected LiveData<Configuration> doInBackground(Configuration... configurations) {
            return configurationDao.queryConfiguration ();
        }
    }

    /**
     * 从数据库中获取Configuration
     * @return Configuration对象
     */
    public LiveData<Configuration> getConfigurationFromDB(){
        ConfigurationAsyncTask configurationAsyncTask = new ConfigurationAsyncTask ();
        return configurationAsyncTask.doInBackground ();
    }


    /**
     * 获取状态码
     * @return 状态码
     */
    public LiveData<Integer> getCode(){
        return errorCodeLiveData;
    }


}
