package activitytest.example.com.network_module.interceptor

import activitytest.example.com.base.util.TokenUtil
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route


class TokenAuthenticator: okhttp3.Authenticator {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun authenticate(route: Route?, response: Response): Request? {
        val code = response.code()

        if (code == 401){
            //token过期
            TokenUtil.deleteToken()
//            if(TokenUtil.isEmptyByToken() == true){
//
//                val defaultRetrofitClient = RetrofitClient.defaultRetrofitClient()
//                val refreshToken = defaultRetrofitClient?.create(Api::class.java)
//                CoroutineScope(Dispatchers.Main).launch {
//                    val responseResult:ResponseResult<Token> = RequestAction.execute {
//                        Log.d(ModuleNames.network_module,"用户名:"+UserUtil.username)
//                        refreshToken!!.refreshToken(User(UserUtil.username, ""))
//                    }
//
//                    if (responseResult.message.equals("刷新成功")){
//                        TokenUtil.saveToken(responseResult.info?.token.toString())
//                    }
//                }
//            }
//


        }




        return response.request()
    }

}