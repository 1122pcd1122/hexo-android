package activitytest.example.com.network_module

import activitytest.example.com.network_module.status.UpdateStatus
import android.util.Log
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RequestAction {

    companion object{
        suspend fun <T> execute(call: suspend () -> ResponseResult<T>): ResponseResult<T> {

            return withContext(Dispatchers.Main){

                //开始

                UpdateStatus.update(status = StartStatus)
                val responseResult: ResponseResult<T>?
                try {
                    responseResult = call.invoke()
                    //成功
                    UpdateStatus.update(status = SuccessStatus)
                }catch (e:Exception){
                    Log.d("network_module","网络错误${e.printStackTrace()}")
                    //失败
                    UpdateStatus.update(status = ErrorStatus)
                    return@withContext ApiException.build(e = e).toResponse()
                }

                //完成
                UpdateStatus.update(COMPLETEStatus)
                return@withContext responseResult
            }
        }

    }


}

// 网络、数据解析错误处理
class ApiException(val code: Int, override val message: String?, override val cause: Throwable? = null)
    : RuntimeException(message, cause) {
    companion object {
        // 网络状态码
        private const val CODE_NET_ERROR = 4000
        private const val CODE_TIMEOUT = 4080
        private const val CODE_JSON_PARSE_ERROR = 4010
        private const val CODE_SERVER_ERROR = 5000
        // 业务状态码
        const val CODE_AUTH_INVALID = 401

        fun build(e: Throwable): ApiException {
            return if (e is HttpException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.code()},${e.message()})")
            } else if (e is UnknownHostException) {
                ApiException(CODE_NET_ERROR, "网络连接失败，请检查后再试")
            } else if (e is SocketTimeoutException) {
                ApiException(CODE_TIMEOUT, "请求超时，请稍后再试")
            } else if (e is IOException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.message})")
            } else if (e is JsonParseException || e is JSONException) {
                // Json解析失败
                ApiException(CODE_JSON_PARSE_ERROR, "数据解析错误，请稍后再试")
            } else {
                ApiException(CODE_SERVER_ERROR, "系统错误(${e.message})")
            }
        }
    }
    fun <T> toResponse(): ResponseResult<T> {
        return ResponseResult(code = code,message = message)
    }
}
