package activitytest.example.com.roomdemo.home

import activitytest.example.com.roomdemo.home.utils.DownAndParseBlog
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce
import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.database.dao.BlogDao
import activitytest.example.com.roomdemo.main.database.dao.BlogTagsDao
import activitytest.example.com.roomdemo.main.database.dao.TagsDao
import activitytest.example.com.roomdemo.main.database.entity.Blog
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer
import java.util.*

class BlogService : LifecycleService(){
    val TAG:String = BlogService::class.java.name

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"开始服务-BlogService")
        val timer = Timer()
        val timerTask:TimerTask =object :TimerTask(){
            override fun run() {
                Log.d(TAG,"开始执行任务-BlogService")
                val homeRepository:HomeRepository = HomeRepository.homeRepository!!
                val downAndParseBlog = DownAndParseBlog()

                val handler = Handler(Looper.getMainLooper())
                handler.post {

                    homeRepository.listBlogInfo().observe(this@BlogService,  {
                        Log.d(TAG,"观察中......")
                        downAndParseBlog.listBlogIntroduce(it).observe(this@BlogService,  {
                           if (MyDatabase.instance?.blogDao?.queryBlog()?.value == null){
                               //添加进入数据库
                               Log.d(TAG,"准备添加...")
                           }
                        })
                    })
                }
            }
        }


        timer.schedule(timerTask,Date(),(1000 * 60 * 60 * 24).toLong())

        return super.onStartCommand(intent, flags, startId)
    }





}