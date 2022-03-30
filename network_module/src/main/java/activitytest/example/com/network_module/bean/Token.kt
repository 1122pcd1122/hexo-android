package activitytest.example.com.network_module.bean
import com.google.gson.annotations.SerializedName


data class Token(val info:String = "",val message:String = "",val token:String = "")
data class Refresh(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("message")
    val message: String = ""
)

data class Info(
    @SerializedName("info")
    val info: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("token")
    val token: String = ""
)