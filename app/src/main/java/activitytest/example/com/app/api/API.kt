package activitytest.example.com.app.api

import activitytest.example.com.app.bean.UserData
import activitytest.example.com.app.bean.UserInfo
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET


interface API {

    /*
        获取用户博客标题
     */
    @GET("userInfo")
    suspend fun configuration(): ResponseResult<UserData>

}