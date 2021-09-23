package activitytest.example.com.log_module.project.repository

import activitytest.example.com.base.IP
import activitytest.example.com.log_module.project.api.API
import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.network_module.RetrofitClient
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class LogRepository {

    companion object{
        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient().createRetrofitClient(IP.httpUrl)
        }
        val logRepository by lazy {
            LogRepository()
        }

        val logService by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    fun log(): Flow<PagingData<ArticleByYears>> {
        return Pager(
                config = PagingConfig(2),
                pagingSourceFactory = {
                    LogPagingSource()
                }
        ).flow


    }



}