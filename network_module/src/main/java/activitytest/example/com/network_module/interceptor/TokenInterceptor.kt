package activitytest.example.com.network_module.interceptor

import activitytest.example.com.base.util.TokenUtil
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val readToken = TokenUtil.readToken()
        val request = chain.request()
        val build = request.newBuilder().addHeader("Authorization", "Bearer $readToken").build()
        return chain.proceed(build)
    }
}