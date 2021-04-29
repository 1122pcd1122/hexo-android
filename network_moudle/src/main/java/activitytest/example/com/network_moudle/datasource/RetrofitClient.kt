package activitytest.example.com.network_moudle.datasource

import activitytest.example.com.network_moudle.callback.BaseRequestCallback
import activitytest.example.com.network_moudle.exception.BaseHttpException
import activitytest.example.com.network_moudle.exception.LocalBadException
import android.util.LruCache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val baseUrl:String = ""

    companion object{



        /*
           默认的OkhttpClient
         */
        private val defaultOkhttpClient: OkHttpClient by lazy {
           OkHttpClient().newBuilder()
                    //添加拦截器
                    .addInterceptor(ClientInterceptor())
                    //设置连接超时时间
                    .connectTimeout(2, TimeUnit.SECONDS)
                    //设置读取超时时间
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build()
        }


        /*
            构建默认的RetrofitClient
         */
         fun createDefaultRetrofitClient(baseUrl:String?):Retrofit?{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultOkhttpClient)
                    .baseUrl(baseUrl)
                    .build()
        }
    }

}