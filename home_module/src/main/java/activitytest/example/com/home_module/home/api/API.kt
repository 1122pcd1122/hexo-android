package activitytest.example.com.home_module.home.api

import activitytest.example.com.home_module.home.bean.ListArticle
import activitytest.example.com.home_module.home.bean.UserData
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET

interface API {

    /*
        获取用户信息
     */
    @GET("userInfo")
    suspend fun configuration():ResponseResult<UserData>

    /*
        文章信息列表
     */
    @GET("article")
    suspend fun articleList():ResponseResult<List<ListArticle>>
}