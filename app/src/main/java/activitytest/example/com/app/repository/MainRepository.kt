package activitytest.example.com.app.repository

import activitytest.example.com.network_module.RetrofitClient

import activitytest.example.com.app.api.API
import activitytest.example.com.app.bean.UserData
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult


class MainRepository {

    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient.defaultRetrofitClient()
        }
        val mainRepository by lazy {
            MainRepository()
        }

        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    /*
        获取博客信息
     */
    suspend fun blogInfo(): ResponseResult<UserData> {
        return RequestAction.execute {
            service?.configuration()!!
        }


    }

}



