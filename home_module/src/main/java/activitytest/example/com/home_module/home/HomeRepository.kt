package activitytest.example.com.home_module.home

import activitytest.example.com.home_module.home.api.API
import activitytest.example.com.home_module.home.bean.ListArticleInfo
import activitytest.example.com.home_module.home.bean.UserInfo
import activitytest.example.com.network_module.NetRequest
import activitytest.example.com.network_module.ResultData
import activitytest.example.com.network_module.datasource.RetrofitClient
import androidx.lifecycle.LiveData

class HomeRepository {

    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://192.168.43.196:8080/")
        }
        val homeRepository by lazy {
            HomeRepository()
        }

        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    /**
     * 所有文章
     */
    fun articleList(): LiveData<ResultData<ListArticleInfo>?> {

        return NetRequest.request() {
            api {
                service?.articleList()!!
            }
        }


    }

    /**
     * 个人信息
     */
     fun configuration(): LiveData<ResultData<UserInfo>?> {

        return NetRequest.request() {
            api {
                service?.configuration()!!
            }
        }

    }
}

