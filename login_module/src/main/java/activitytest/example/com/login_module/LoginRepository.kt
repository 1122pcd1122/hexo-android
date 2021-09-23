package activitytest.example.com.login_module

import activitytest.example.com.base.IP
import activitytest.example.com.login_module.api.API
import activitytest.example.com.login_module.bean.Token
import activitytest.example.com.login_module.bean.User
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient

object LoginRepository {

    private val retrofitClient by lazy {
        val retrofitClient = RetrofitClient().createRetrofitClient(IP.httpUrl)
        retrofitClient?.create(API::class.java)
    }

    suspend fun login(user: User): ResponseResult<Token> {
        return RequestAction.execute {
            retrofitClient?.loginInfo(user)!!
        }
    }
}