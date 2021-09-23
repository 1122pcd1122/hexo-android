package activitytest.example.com.login_module.api

import activitytest.example.com.login_module.bean.Token
import activitytest.example.com.login_module.bean.User
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("signIn")
    fun loginInfo(@Body user: User):ResponseResult<Token>
}