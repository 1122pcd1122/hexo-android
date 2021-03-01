package activitytest.example.com.roomdemo.main.utils;

public enum ErrorCodeEnum {
    SUCCESS(200,"成功"),
    ERROR_API(404,"API出现错误"),
    NO_NETWORK(400,"无网络")
    ;

    /**
     * 状态码
     */
    private Integer errorCode;
    /**
     * 状态信息
     */
    private String errorMsg;

    ErrorCodeEnum() {
    }

    ErrorCodeEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
