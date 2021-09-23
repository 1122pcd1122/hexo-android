package activitytest.example.com.log_module.project.api

import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.log_module.project.bean.LogBean
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET

interface API {
    @GET("articleByYear")
    suspend fun log():ResponseResult<List<ArticleByYears>>
}