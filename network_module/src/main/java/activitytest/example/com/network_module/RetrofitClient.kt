package activitytest.example.com.network_module


import android.util.Log
import com.google.gson.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

class RetrofitClient {


    //默认okhttpClient
    private var okClient: OkHttpClient? = ClientOkhttp.okHttpClient

    companion object {
        var baseUrl: String? = null
            set(value) {
                Log.d("network_module", "baseUrl设置为$baseUrl")
                field = value
            }

    }

    /**
     * 设置okhttp客户端
     */
    fun updateOkhttpClient(okHttpClient: OkHttpClient?) {
        this.okClient = okHttpClient
    }

    /**
     * 获取OkhttpClient
     */
    private fun getOkhttpClient(): OkHttpClient? {
        return okClient
    }

    //创建RetrofitClient客户端
    fun createRetrofitClient(baseUrl: String?): Retrofit? {

        Companion.baseUrl = baseUrl

        return Retrofit.Builder()
                .client(okClient!!)
                .baseUrl(Companion.baseUrl ?: "")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }




}


