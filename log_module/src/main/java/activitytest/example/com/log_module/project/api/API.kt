package activitytest.example.com.log_module.project.api

import activitytest.example.com.log_module.project.bean.ListArticleInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface API {
    @GET("articleByYear")
    fun listBlogByYear(@Query("year") year:Int): Call<ListArticleInfo>
}