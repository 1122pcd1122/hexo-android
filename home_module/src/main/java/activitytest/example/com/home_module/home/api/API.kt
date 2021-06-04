package activitytest.example.com.home_module.home.api

import activitytest.example.com.home_module.home.bean.ListArticleInfo
import activitytest.example.com.home_module.home.bean.UserInfo
import retrofit2.http.GET

interface API {

    /*
        获取用户信息
     */
    @GET("userInfo")
    suspend fun configuration():UserInfo

    /*
        文章信息列表
     */
    @GET("article")
    suspend fun articleList():ListArticleInfo
}