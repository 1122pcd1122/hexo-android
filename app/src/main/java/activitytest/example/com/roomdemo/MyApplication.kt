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
        myDatabase = MyDatabase.instance
        api = RetrofitClient.createService(API::class.java)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
        var api: API? = null
            private set
        var myDatabase: MyDatabase? = null
            private set
    }
}