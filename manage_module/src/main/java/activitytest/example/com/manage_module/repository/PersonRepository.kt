package activitytest.example.com.manage_module.repository

import activitytest.example.com.base.IP
import activitytest.example.com.network_module.RetrofitClient
import activitytest.example.com.manage_module.api.API
import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult

class PersonRepository {

    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient().createRetrofitClient(IP.httpUrl)
        }
        val personRepository by lazy {
            PersonRepository()
        }
        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    suspend fun userInfo(): ResponseResult<UserData> {

        return RequestAction.execute {
            service?.configuration()!!
        }
    }

    suspend fun articleNum(): ResponseResult<Int> {
        return RequestAction.execute {
            service?.articleNum()!!
        }
    }

    suspend fun tagNum(): ResponseResult<Int> {
        return RequestAction.execute {
            service?.labelNum()!!
        }
    }
}