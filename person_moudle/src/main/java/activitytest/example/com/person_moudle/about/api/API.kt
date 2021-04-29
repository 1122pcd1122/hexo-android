package activitytest.example.com.person_moudle.about.api

import activitytest.example.com.person_moudle.about.bean.ArticleNum
import activitytest.example.com.person_moudle.about.bean.ConfigurationBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import activitytest.example.com.person_moudle.about.bean.TgsNum

interface API {
    /*
        获取用户信息
     */
    @GET("configuration")
    fun configuration(@Query("name") name: String): Call<ConfigurationBean>

    @GET("articleNum")
    fun articleNum(): Call<ArticleNum>

    @GET("tagsNum")
    fun tagsNum():Call<TgsNum>

}
