package activitytest.example.com.roomdemo.main

import activitytest.example.com.roomdemo.MyApplication
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import activitytest.example.com.roomdemo.main.database.dao.ConfigurationDao
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.lifecycle.LifecycleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
                //切换到主线程
                GlobalScope.launch(Dispatchers.Main) {
                    configurationMutableLiveData!!.observe(this@ConfigurationService, { configuration ->

                        GlobalScope.launch {
                            if (configuration != null) {
                                updateOrInsertConf(configuration)
                            }
                        }
                        configurationMutableLiveData.removeObservers(this@ConfigurationService)
                    })
                }
            }
        }
        timer.schedule(timerTask, Date(), (1000 * 60 * 60 * 24).toLong())
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 将获取的数据更新到数据库如果没有数据则插入数据
     * @param c Configuration对象
     */
     fun updateOrInsertConf(c: Configuration) {

            val configurationDao: ConfigurationDao = MyApplication.myDatabase?.configurationDao!!
            val configurationLiveData = configurationDao.queryConfiguration()
            GlobalScope.launch(Dispatchers.Main) {
                configurationLiveData?.observe(this@ConfigurationService, { configuration ->
                    GlobalScope.launch {
                        if (configuration != null) {
                           Log.d(tag, "更新数据")
                           configurationDao.updateConfiguration(c)
                        } else {
                           Log.d(tag, "插入数据")
                           configurationDao.insertConfiguration(c)
                     }
                    }
                    Log.d(tag, "移除观察")
                    configurationLiveData.removeObservers(this@ConfigurationService)
            })
        }

    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onDestroy() {
        stopSelf()
        super.onDestroy()
    }

}