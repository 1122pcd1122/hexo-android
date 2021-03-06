package activitytest.example.com.roomdemo.main

import activitytest.example.com.roomdemo.MyApplication
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.database.dao.ConfigurationDao
import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.http.API
import activitytest.example.com.roomdemo.main.utils.ErrorCodeEnum
import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainRepository {
    private val api: API? = MyApplication.api
    private val tag = MainRepository::class.java.name
    private var configurationDao: ConfigurationDao? = null
    private val errorCodeLiveData: MutableLiveData<Int?>
    private val configurationMutableLiveData: MutableLiveData<Configuration?>

    /**
     * 通过Retrofit获取配置文件
     * @return Configuration对象实例
     */
    val configuration: LiveData<Configuration?>
        get() {
            api!!.getConfiguration("https://raw.githubusercontent.com/1122pcd1122/MyNotes/master/configuration.json")
                    ?.enqueue(object : Callback<Configuration?> {
                override fun onResponse(call: Call<Configuration?>, response: Response<Configuration?>) {
                    Log.d(tag, "网络连接成功")
                    Log.d(tag, response.code().toString() + "")
                    if (response.code() == ErrorCodeEnum.SUCCESS.errorCode) {
                        Log.d(tag, response.body().toString())
                        errorCodeLiveData.postValue(ErrorCodeEnum.SUCCESS.errorCode)
                        configurationMutableLiveData.postValue(response.body())
                    } else {
                        errorCodeLiveData.postValue(ErrorCodeEnum.ERROR_API.errorCode)
                        configurationMutableLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Configuration?>, t: Throwable) {
                    Log.d(tag, "网络连接失败")
                    Log.d(tag, Objects.requireNonNull(t.message)+"")
                    errorCodeLiveData.postValue(400)
                    configurationMutableLiveData.postValue(null)
                }
            })


            return configurationMutableLiveData
        }

    /**
     * 从数据库中查询的任务线程
     */
    @SuppressLint("StaticFieldLeak")
    internal inner class ConfigurationAsyncTask : AsyncTask<Configuration?, Int?, LiveData<Configuration?>?>() {
        public override fun doInBackground(vararg params: Configuration?): LiveData<Configuration?>? {
            return configurationDao!!.queryConfiguration()
        }
    }

    /**
     * 从数据库中获取Configuration
     * @return Configuration对象
     */
    val configurationFromDB: LiveData<Configuration?>?
        get() {
            val configurationAsyncTask = ConfigurationAsyncTask()
            return configurationAsyncTask.doInBackground()
        }

    /**
     * 获取状态码
     * @return 状态码
     */
    val code: LiveData<Int?>
        get() = errorCodeLiveData

    companion object {
        private var mainRepository: MainRepository? = null

        /**
         * 获取主仓库实例
         * @return mainRepository对象
         */
        val instance: MainRepository?
            get() {
                if (mainRepository == null) {
                    mainRepository = MainRepository()
                }
                return mainRepository
            }
    }

    /**
     * 主仓库构造方法
     * 获取数据库对象及Configuration的Dao层
     */
    init {
        val myDatabase: MyDatabase? = MyApplication.myDatabase
        if (myDatabase != null) {
            configurationDao = myDatabase.configurationDao
        }
        errorCodeLiveData = MutableLiveData()
        configurationMutableLiveData = MutableLiveData()
    }
}



