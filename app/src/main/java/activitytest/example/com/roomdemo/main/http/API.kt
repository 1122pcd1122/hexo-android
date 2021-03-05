package activitytest.example.com.roomdemo.main.http

import activitytest.example.com.roomdemo.home.bean.Root
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *
 */
interface API {
    @get:GET("repos/1122pcd1122/MyNotes/contents/blog_post?ref=master")
    val call: Call<List<Root?>?>?

    @GET
    fun downLoadMarkDown(@Url url: String?): Call<ResponseBody?>?

    @GET
    fun getConfiguration(@Url url: String?): Call<Configuration?>?
}