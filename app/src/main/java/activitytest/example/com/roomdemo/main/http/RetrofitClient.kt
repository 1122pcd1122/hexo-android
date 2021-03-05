package activitytest.example.com.roomdemo.main.http

import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 客户端
 */
object RetrofitClient {
    private val TAG = HomeLifeCycle::class.java.name
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        Log.d(TAG, "API对象加载完毕")
        return retrofit.create(serviceClass)
    }
}