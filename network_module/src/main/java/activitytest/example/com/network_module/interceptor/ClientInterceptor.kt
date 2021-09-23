package activitytest.example.com.network_module.interceptor

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.lang.StringBuilder

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
        Log.d("------------客户端(请求)拦截器---------", "https: $https "
                                                            +"请求连接: $url "
                                                            +"请求方法: $method "
                                                            +"请求头: $headers "
                                                            +"请求体: $body \n"
        )

        val response = chain?.proceed(request!!)
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

        Log.d("------------客户端(响应)拦截器---------","\n 重定向: $redirect "
                                                            +"请求状态: $successful "
                                                            +"状态码: $code "
                                                            +"响应头: $responseHeader "
                                                            +"响应体: ${getResponseStr(responseBody)} \n")
        return chain?.proceed(request!!)
    }

    /**
     * 获取响应体内容(字符串)
     */
    private fun getResponseStr(responseBody: ResponseBody?) {

        val bufferedReader = BufferedReader( responseBody?.charStream())
        val responseText = StringBuilder()
        bufferedReader.forEachLine {
            responseText.append(it + "\n")
        }
    }
}