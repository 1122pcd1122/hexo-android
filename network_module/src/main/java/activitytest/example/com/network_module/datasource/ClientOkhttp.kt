package activitytest.example.com.network_module.datasource

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/*
    Okhttp客户端相关配置
 */
class ClientOkhttp {
    companion object{
        val okHttpClient:OkHttpClient = OkHttpClient().newBuilder()
                //添加拦截器
                .addInterceptor(ClientInterceptor())
                //设置连接超时时间
                .connectTimeout(20,TimeUnit.SECONDS)
                //设置读取超时时间
                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();    }
}