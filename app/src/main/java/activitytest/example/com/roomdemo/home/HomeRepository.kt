package activitytest.example.com.roomdemo.home

import activitytest.example.com.roomdemo.home.bean.Root
import activitytest.example.com.roomdemo.main.http.API
import activitytest.example.com.roomdemo.main.http.RetrofitClient
import android.util.ArraySet
import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import org.markdown4j.Markdown4jProcessor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class HomeRepository {
    private val blogIntroduce: API?
    fun listBlogInfo(): MutableLiveData<List<Root?>?> {
        val mutableLiveData = MutableLiveData<List<Root?>?>()
        val call = blogIntroduce?.call
        call!!.enqueue(object : Callback<List<Root?>?> {
            override fun onResponse(call: Call<List<Root?>?>?, response: Response<List<Root?>?>?) {
                Log.d(TAG, "成功")
                if (response != null) {
                    Log.d(TAG, response.body()?.size.toString() + "")
                }
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "正在添加......")
                        mutableLiveData.postValue(response.body())
                        Log.d(TAG, "添加成功......")
                    }
                }
            }

            override fun onFailure(call: Call<List<Root?>?>?, t: Throwable?) {
                Log.d(TAG, "失败")
                if (t != null) {
                    Log.d(TAG, t.message+"")
                }
            }
        })
        return mutableLiveData
    }

    fun downLoadBlogContent(blogContent: List<String?>): MutableLiveData<Set<String>> {
        val contentMutableList = MutableLiveData<Set<String>>()
        val contentList: MutableSet<String> = ArraySet()
        for (i in blogContent.indices) {
            val url = blogContent[i]
            val responseBodyCall = blogIntroduce!!.downLoadMarkDown(url)
            responseBodyCall!!.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                    try {
                        if (response != null) {
                            Log.d(TAG, "成功" + response.body().toString())
                        }
                        val string = response?.body()?.string()
                        val html = Markdown4jProcessor().process(string)
                        Log.d(TAG, html)
                        contentList.add(html)
                        if (blogContent.size - 1 == i) {
                            contentMutableList.postValue(contentList)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                    Log.d(TAG, "下载失败")
                }
            })
        }
        return contentMutableList
    }

    companion object {
        private val TAG = HomeRepository::class.java.name
        var homeRepository: HomeRepository? = null
        val instance: HomeRepository?
            get() {
                if (homeRepository == null) {
                    homeRepository = HomeRepository()
                }
                return homeRepository
            }
    }

    init {
        blogIntroduce = RetrofitClient.createService(API::class.java)
    }
}