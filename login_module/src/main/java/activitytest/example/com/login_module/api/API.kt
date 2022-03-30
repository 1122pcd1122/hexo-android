package activitytest.example.com.login_module.api


import activitytest.example.com.login_module.bean.*
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {
    @POST("signIn")
    suspend fun loginInfo(@Body user: User):ResponseResult<LResponse>

    @POST("signUp")
    suspend fun registerInfo(@Body registerInform: RegisterInform):ResponseResult<RResponse>

    @GET("isContainName")
    suspend fun isContainName(@Query("name") name:String):ResponseResult<Boolean>

    @GET("isContainRepository")
    suspend fun isContainRepository(@Query("repositoryName") repositoryName:String):ResponseResult<Boolean>
}