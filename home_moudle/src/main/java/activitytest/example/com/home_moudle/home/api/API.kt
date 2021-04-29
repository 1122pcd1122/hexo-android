package activitytest.example.com.home_moudle.home.api

import activitytest.example.com.home_moudle.home.bean.ArticleBean
import activitytest.example.com.home_moudle.home.bean.ConfigurationBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface API {

    /*
        获取用户信息
     */
    @GET("configuration")
    fun configuration(@Query("name") name: String):Call<ConfigurationBean>

    /*
        文章信息列表
     */
    @GET("blog")
    fun articleList():Call<ArticleBean>
}