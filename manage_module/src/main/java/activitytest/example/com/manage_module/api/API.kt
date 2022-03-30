package activitytest.example.com.manage_module.api

import activitytest.example.com.manage_module.bean.*
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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

    @POST("updateBlogName")
    suspend fun changeBlogName(@Body blogName: UpdateConfig):ResponseResult<String>


    @POST("updateLocation")
    suspend fun changeLocation(@Body location:UpdateConfig):ResponseResult<String>


    @POST("updateSignature")
    suspend fun changeSignature(@Body signature:UpdateConfig):ResponseResult<String>

    @POST("updateEmail")
    suspend fun changeEmail(@Body email:UpdateConfig):ResponseResult<String>
}
