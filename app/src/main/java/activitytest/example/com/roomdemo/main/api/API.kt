package activitytest.example.com.roomdemo.main.api

import activitytest.example.com.roomdemo.main.bean.ConfigurationBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    /*
        获取用户信息
     */
    @GET("configuration")
    fun configuration(@Query("name") name: String): Call<ConfigurationBean>



}