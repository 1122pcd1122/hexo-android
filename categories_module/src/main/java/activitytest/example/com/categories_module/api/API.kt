package activitytest.example.com.categories_module.api

import activitytest.example.com.categories_module.bean.Article
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface API{

    @GET("categoriesNum")
    suspend fun categoriesNum():ResponseResult<Int>

    @GET("allCategories")
    suspend fun categories():ResponseResult<List<String>>

    @GET("articlesByCategories")
    suspend fun articleByCategories(@Query("categories") categories:String):ResponseResult<List<Article>>
}