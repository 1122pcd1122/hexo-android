package activitytest.example.com.roomdemo.repository

import activitytest.example.com.network_module.NetRequest
import activitytest.example.com.network_module.ResultData
import activitytest.example.com.network_module.datasource.RetrofitClient

import activitytest.example.com.roomdemo.api.API
import activitytest.example.com.roomdemo.bean.UserInfo
import androidx.lifecycle.LiveData


class MainRepository {

    companion object{

        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://192.168.43.196:8080/")
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
        blogName 博客名
     */
    fun blogInfo():LiveData<ResultData<UserInfo>?>{
        return NetRequest.request() {
            api{
                service?.configuration()!!
            }
        }


    }



}



