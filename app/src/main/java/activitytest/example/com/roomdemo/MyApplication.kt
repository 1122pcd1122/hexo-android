package activitytest.example.com.roomdemo

import activitytest.example.com.roomdemo.main.database.MyDatabase
import activitytest.example.com.roomdemo.main.http.API
import activitytest.example.com.roomdemo.main.http.RetrofitClient
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
        val api: API? by lazy {
            RetrofitClient.createService(API::class.java)
        }

        val myDatabase: MyDatabase? by lazy {
            MyDatabase.instance!!
        }


    }


}