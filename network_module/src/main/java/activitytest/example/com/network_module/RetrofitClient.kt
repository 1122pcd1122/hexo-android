package activitytest.example.com.network_module


import activitytest.example.com.base.IP
import android.util.Log
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    //默认的URL 通过获取base模块的IP地址来配置
    companion object {

        var baseUrl: String? = IP.httpUrl

        /**
         * 默认的RetrofitClient配置
         *
         */
        fun defaultRetrofitClient():Retrofit?{
            return Retrofit.Builder().
                    client(ClientOkhttp.createOkhttpClient(true)).
                    baseUrl(baseUrl!!).
                    addConverterFactory(GsonConverterFactory.create()
                    ).
            build()
        }

        /**
         * 想默认的客户端中添加拦截器
         * @param interceptor 拦截器
         */
        fun addInterceptor(interceptor: Interceptor){
            ClientOkhttp.defaultOkHttpClient.interceptors().add(interceptor)
        }

        /**
         * 更改URL
         */
        fun changeBaseUrl(url:String){
            baseUrl = url
            Log.d("network_module","更改url为:$url")
        }

    }





}


