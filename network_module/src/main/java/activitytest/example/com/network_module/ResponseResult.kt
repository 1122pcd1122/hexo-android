package activitytest.example.com.network_module

import com.google.gson.annotations.SerializedName


/**
 * 响应信息封装
 */
data class ResponseResult<T>(
        //状态
        @SerializedName("code")
        val code: Int? = null,
        //响应信息
        @SerializedName("info")
        val info:T? = null,
        //描述信息
        @SerializedName("message")
        val message:String? = null
)
