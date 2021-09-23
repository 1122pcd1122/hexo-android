package activitytest.example.com.network_module


/**
 * 响应信息封装
 */
data class ResponseResult<T>(
        //状态
        val code: Int? = null,
        //响应信息
        val info:T? = null,
        //描述信息
        val message:String? = null
)
