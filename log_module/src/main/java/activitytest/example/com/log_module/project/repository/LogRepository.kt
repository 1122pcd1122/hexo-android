package activitytest.example.com.log_module.project.repository

import activitytest.example.com.log_module.project.api.API
import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class LogRepository {

    companion object{
        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient.defaultRetrofitClient()
        }
        val logRepository by lazy {
            LogRepository()
        }

        val logService by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    suspend fun log(): ResponseResult<List<ArticleByYears>> {
        return RequestAction.execute {
            logService?.log()!!
        }


    }



}