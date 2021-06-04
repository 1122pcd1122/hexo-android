package activitytest.example.com.roomdemo.api

import activitytest.example.com.roomdemo.bean.UserInfo
import retrofit2.http.GET


interface API {

    /*
        获取用户博客标题
     */
    @GET("userInfo")
    suspend fun configuration(): UserInfo




}