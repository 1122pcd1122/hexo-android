package activitytest.example.com.login_module

import activitytest.example.com.login_module.api.API
import activitytest.example.com.login_module.bean.*
import activitytest.example.com.network_module.RequestAction
import activitytest.example.com.network_module.ResponseResult
import activitytest.example.com.network_module.RetrofitClient

object LoginRepository {

    private val retrofitClient by lazy {
        val retrofitClient = RetrofitClient.defaultRetrofitClient()
        return@lazy retrofitClient?.create(API::class.java)
    }

    suspend fun login(user: User): ResponseResult<LResponse> {
        return RequestAction.execute {
            retrofitClient?.loginInfo(user)!!
        }
    }

    suspend fun register(registerInform: RegisterInform):ResponseResult<RResponse>{
        return RequestAction.execute {
            retrofitClient?.registerInfo(registerInform = registerInform)!!
        }
    }

    suspend fun isContainName(name:String):ResponseResult<Boolean>{
        return RequestAction.execute {
            retrofitClient?.isContainName(name = name)!!
        }
    }

    suspend fun isContainRepository(repositoryName:String):ResponseResult<Boolean>{
        return RequestAction.execute {
            retrofitClient?.isContainRepository(repositoryName = repositoryName)!!
        }
    }
}