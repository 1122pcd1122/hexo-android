package activitytest.example.com.categories_module.repository

import activitytest.example.com.categories_module.api.API
import activitytest.example.com.categories_module.bean.Article
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient

class CategoriesRepository {
    companion object{
        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient.defaultRetrofitClient()
        }
        val categoriesRepository by lazy {
            CategoriesRepository()
        }
        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }


    suspend fun categoriesNum():ResponseResult<Int>{
        return RequestAction.execute {
            service?.categoriesNum()!!
        }
    }

    suspend fun categories():ResponseResult<List<String>>{
        return RequestAction.execute {
            service?.categories()!!
        }
    }

    suspend fun articleByCategories(categories:String):ResponseResult<List<Article>>{
        return RequestAction.execute {
            service?.articleByCategories(categories = categories)!!
        }
    }
}