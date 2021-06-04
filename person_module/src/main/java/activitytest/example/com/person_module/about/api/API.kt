package activitytest.example.com.person_module.about.api

import activitytest.example.com.person_module.about.bean.ArticleNum
import activitytest.example.com.person_module.about.bean.LabelNum
import activitytest.example.com.person_module.about.bean.UserInfo
import retrofit2.http.GET

interface API {
    /*
        获取用户信息
     */
    @GET("userInfo")
    suspend fun configuration(): UserInfo

    @GET("articleNum")
    suspend fun articleNum(): ArticleNum

    @GET("labelNum")
    suspend fun labelNum(): LabelNum

}
