package activitytest.example.com.network_module

import activitytest.example.com.network_module.bean.Token
import activitytest.example.com.network_module.bean.User
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("/refresh")
    suspend fun refreshToken(@Body user: User):ResponseResult<Token>
}