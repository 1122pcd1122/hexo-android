package activitytest.example.com.network_module


import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.network_module.interceptor.ClientInterceptor
import activitytest.example.com.network_module.interceptor.TokenAuthenticator
import activitytest.example.com.network_module.interceptor.TokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/*
    Okhttp客户端相关配置
 */
class ClientOkhttp {
    companion object{

        /**
         * 默认的okhttpClient 没有拦截器
         */
        val defaultOkHttpClient:OkHttpClient = OkHttpClient().newBuilder()
                //设置连接超时时间
                .connectTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(ClientInterceptor())
                //设置读取超时时间
                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()

        fun createOkhttpClient(isIntercept:Boolean): OkHttpClient {
            val builder = OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)

            if (isIntercept){
                builder.addInterceptor(TokenInterceptor()).authenticator(TokenAuthenticator())
            }

            return builder.addInterceptor(ClientInterceptor()).build()
        }

    }


}