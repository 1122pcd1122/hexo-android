package activitytest.example.com.app.bean

data class UserInfo<T> (
        var code: Int = 0,
        var userData: T? = null,
        var message: String? = null
)