package activitytest.example.com.home_module.home

import activitytest.example.com.home_module.home.api.API
import activitytest.example.com.home_module.home.bean.Article
import activitytest.example.com.home_module.home.bean.UserData

import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient


class HomeRepository {

    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
           RetrofitClient.defaultRetrofitClient()
        }
         val homeRepository by lazy {
            HomeRepository()
        }

         val homeService by lazy {
            retrofitClient?.create(API::class.java)
        }

    }

    /**
     * 所有文章
     */
    suspend fun articleList(): ResponseResult<List<Article>> {

        return RequestAction.execute {
            homeService?.articleList()!!
        }


    }

    /**
     * 个人信息
     */
    suspend fun configuration(): ResponseResult<UserData> {

        return RequestAction.execute {
            homeService?.configuration()!!
        }
    }

    /**
     * 页数
     */
    suspend fun pageNum():ResponseResult<Int>{
        return RequestAction.execute {
            homeService?.pageNum()!!
        }
    }

    suspend fun articleByPage(page:Int):ResponseResult<List<Article>>{
        return RequestAction.execute {
            homeService?.articleByPage(page)!!
        }
    }
}

