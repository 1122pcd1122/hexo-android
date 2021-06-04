package activitytest.example.com.network_module.datasource


import com.google.gson.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
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

        private fun gsonTime():Gson{
          return  GsonBuilder().registerTypeAdapter(Date::class.java,object :JsonDeserializer<Date>{
                override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
                    return json?.asJsonPrimitive?.asLong?.let { Date(it) }
                }

            }).create()
        }


        /*
            构建默认的RetrofitClient
         */
         fun createDefaultRetrofitClient(baseUrl:String?):Retrofit?{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gsonTime()))
                    .client(defaultOkhttpClient)
                    .baseUrl(baseUrl!!)
                    .build()
        }
    }

}