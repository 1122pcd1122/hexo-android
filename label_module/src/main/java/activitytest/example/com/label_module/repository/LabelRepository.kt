package activitytest.example.com.label_module.repository

import activitytest.example.com.base.IP
import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.api.API
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient

class LabelRepository {
    companion object{
        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient().createRetrofitClient(IP.httpUrl)
        }
        val labelRepository by lazy {
            LabelRepository()
        }
        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    suspend fun listLabels():ResponseResult<List<String>>  {
        return RequestAction.execute {
            service?.listLabels()!!
        }
    }

    suspend fun articles(label:String): ResponseResult<List<Article>> {
        return RequestAction.execute {
            service?.articles(label)!!
        }
    }
}