package activitytest.example.com.log_moudle.project.api

import activitytest.example.com.log_moudle.project.bean.ArticlesByYear
import retrofit2.Call
import retrofit2.http.GET


interface API {
    @GET("blogAllByYear")
    fun listBlogByYear():Call<ArticlesByYear>
}