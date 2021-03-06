package activitytest.example.com.roomdemo.main

import activitytest.example.com.roomdemo.MyApplication
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.database.dao.ConfigurationDao
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.lifecycle.LifecycleService
import java.util.*

class ConfigurationService : LifecycleService() {
    private val tag = ConfigurationService::class.java.name
    override fun onCreate() {
        Log.d(tag, "创建服务")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "开始服务......")
        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                Log.d(tag, "开始任务")
                val instance: MainRepository? = MainRepository.instance
                val configurationMutableLiveData = instance?.configuration
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    configurationMutableLiveData!!.observe(this@ConfigurationService, { configuration ->
                        if (configuration != null) {
                            Log.d(tag, configuration.id.toString() + "")
                            Log.d(tag, configuration.name+"")
                            Log.d(tag,configuration.user_Icon+"")
                            Log.d(tag, configuration.introduce+"")
                            Log.d(tag, configuration.blog_username+"")
                            Log.d(tag, configuration.icon_night+"")
                            Log.d(tag,configuration.me+"")
                            Log.d(tag, configuration.icon_white+"")
                            Log.d(tag,configuration.self_experience+"")
                            Log.d(tag, configuration.listHeadlines+"")
                            manageDatabase(configuration)
                        }
                        configurationMutableLiveData.removeObservers(this@ConfigurationService)
                    })
                }
            }
        }
        timer.schedule(timerTask, 1000*60*60, (1000 * 60 * 60 * 24).toLong())
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 将获取的数据更新到数据库
     * @param c Configuration对象
     */
    fun manageDatabase(c: Configuration?) {
        val configurationDao: ConfigurationDao = MyApplication.myDatabase?.configurationDao!!
        val configurationLiveData = configurationDao.queryConfiguration()
        configurationLiveData!!.observe(this, { configuration ->
            if (configuration != null) {
                Log.d(tag, "更新数据")
                UpdateAsyncTask(configurationDao).doInBackground(c!!)
            } else {
                Log.d(tag, "插入数据")
                InsertAsyncTask(configurationDao).doInBackground(c!!)
            }
            Log.d(tag, "移除观察")
            configurationLiveData.removeObservers(this@ConfigurationService)
        })
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onDestroy() {
        stopSelf()
        super.onDestroy()
    }

    internal class InsertAsyncTask(private var configurationDao: ConfigurationDao) : AsyncTask<Configuration?, Int?, Int?>() {
        public override fun doInBackground(vararg params: Configuration?): Int? {
            configurationDao.insertConfiguration(params)
            return null
        }

    }

    internal class UpdateAsyncTask(private var configurationDao: ConfigurationDao) : AsyncTask<Configuration?, Int?, Int?>() {

        public override fun doInBackground(vararg params: Configuration?): Int? {
            configurationDao.updateConfiguration(params)
            return null
        }
    }
}