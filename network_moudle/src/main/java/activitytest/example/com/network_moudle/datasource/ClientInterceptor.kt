package activitytest.example.com.network_moudle.datasource

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/*
    客户端拦截器配置
 */
class ClientInterceptor :Interceptor{
    @SuppressLint("LongLogTag")
    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request = chain?.request()
        //是否是https
        val https = request?.isHttps
        //请求头
        val headers = request?.headers()
        //请求体
        val body = request?.body()
        //请求方法
        val method = request?.method()
        //请求链接
        val url = request?.url()
        Log.d("------------客户端(请求)拦截器---------", "\nhttps: $https"
                                                            +"请求连接: $url"
                                                            +"请求方法: $method"
                                                            +"请求头: $headers"
                                                            +"请求体: $body"
        )

        val response = chain?.proceed(request)
        //是否重定向
        val redirect = response?.isRedirect
        //是否请求成功
        val successful = response?.isSuccessful
        //响应体
        val responseBody = response?.body()
        //响应状态码
        val code = response?.code()
        //响应头信息
        val responseHeader = response?.headers()


        Log.d("------------客户端(响应)拦截器---------","\n 重定向: $redirect \n"
                                                            +"请求状态: $successful \n"
                                                            +"状态码: $code \n"
                                                            +"响应头: $responseHeader \n"
                                                            +"响应体: $responseBody ")
        return chain?.proceed(request)
    }
}