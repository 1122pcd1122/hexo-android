package activitytest.example.com.person_module.about.reposiory

import activitytest.example.com.network_module.NetRequest
import activitytest.example.com.network_module.ResultData
import activitytest.example.com.network_module.datasource.RetrofitClient
import activitytest.example.com.person_module.about.api.API
import activitytest.example.com.person_module.about.bean.ArticleNum
import activitytest.example.com.person_module.about.bean.LabelNum
import activitytest.example.com.person_module.about.bean.UserInfo
import androidx.lifecycle.LiveData

class PersonRepository {
    private lateinit var api: API


    companion object{

        //retrofit客户端
        private val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://192.168.43.196:8080/")
        }
        val personRepository by lazy {
            PersonRepository()
        }
        private val service by lazy {
            retrofitClient?.create(API::class.java)
        }
    }

    fun userInfo(): LiveData<ResultData<UserInfo>?> {

        return NetRequest.request<UserInfo>() {
            api{
                service?.configuration()!!
            }
        }
    }

    fun articleNum():LiveData<ResultData<ArticleNum>?>{


        return NetRequest.request<ArticleNum>() {
            api {
                service?.articleNum()!!
            }
        }
    }

    fun tagNum():LiveData<ResultData<LabelNum>?>{


        return NetRequest.request<LabelNum>() {
            api {
                service?.labelNum()!!
            }
        }
    }
}