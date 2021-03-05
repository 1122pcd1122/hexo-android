package activitytest.example.com.roomdemo.main.utils

enum class ErrorCodeEnum {
    SUCCESS(200, "成功"), ERROR_API(404, "API出现错误"), NO_NETWORK(400, "无网络");

    /**
     * 状态码
     */
    var errorCode: Int? = null

    /**
     * 状态信息
     */
    var errorMsg: String? = null

    constructor() {}
    constructor(errorCode: Int, errorMsg: String) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }
}