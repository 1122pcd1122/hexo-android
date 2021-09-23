package activitytest.example.com.manage_module.api

import activitytest.example.com.manage_module.bean.ArticleNum
import activitytest.example.com.manage_module.bean.LabelNum
import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.manage_module.bean.UserInfo
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET

interface API {
    /*
        获取用户信息
     */
    @GET("userInfo")
    suspend fun configuration(): ResponseResult<UserData>

    @GET("articleNum")
    suspend fun articleNum(): ResponseResult<Int>

    @GET("labelNum")
    suspend fun labelNum(): ResponseResult<Int>

}
