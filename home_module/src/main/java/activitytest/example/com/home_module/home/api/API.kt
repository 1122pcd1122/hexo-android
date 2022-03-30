package activitytest.example.com.home_module.home.api

import activitytest.example.com.home_module.home.bean.Article
import activitytest.example.com.home_module.home.bean.UserData
import activitytest.example.com.network_module.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    /*
    获取用户博客标题
 */
    @GET("userInfo")
    suspend fun configuration(): ResponseResult<UserData>

    /*
        文章信息列表
     */
    @GET("article")
    suspend fun articleList():ResponseResult<List<Article>>

    /**
     * 页数
     */
    @GET("pageNum")
    suspend fun pageNum():ResponseResult<Int>

    @GET("articleByPage")
    suspend fun articleByPage(@Query("page") page:Int):ResponseResult<List<Article>>
}