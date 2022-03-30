package activitytest.example.com.network_module.interceptor


import activitytest.example.com.base.util.TokenUtil
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/*
    客户端拦截器配置
 */
class ClientInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain?): Response? {

        val request = chain?.request()
        return logResponse(chain,request)
    }

    private fun logResponse(chain: Interceptor.Chain?, request: Request?): Response? {
        val response = chain?.proceed(request!!)
        //是否重定向
        val redirect = response?.isRedirect
        //是否请求成功
        val successful = response?.isSuccessful
        //响应状态码
        val code = response?.code()
        //响应头信息
        val responseHeader = response?.headers()
        Log.d("------------客户端(请求)拦截器---------", "\n 是否为https: ${request?.url()?.isHttps} \n"
                + "请求url: ${request?.url()?.encodedPath()} \n"
                + "请求头: ${request?.headers()?.names().toString()} \n"
                + "请求体: ${request?.body().toString()} \n")

        Log.d("------------客户端(响应)拦截器---------", "\n 重定向: $redirect \n"
                + "请求状态: $successful \n"
                + "状态码: $code \n"
                + "响应头: $responseHeader \n"
                + "响应体: ${response.toString()} \n")
        return response
    }


}