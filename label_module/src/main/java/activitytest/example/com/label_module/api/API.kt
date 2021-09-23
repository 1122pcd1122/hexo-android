package activitytest.example.com.label_module.api

import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.Articles
import activitytest.example.com.label_module.LabelsBean
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query


interface API {

    @GET("labels")
    suspend fun listLabels(): ResponseResult<List<String>>

    @GET("articles")
    suspend fun articles(@Query("label") label:String): ResponseResult<List<Article>>
}


