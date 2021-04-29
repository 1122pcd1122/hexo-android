package activitytest.example.com.home_moudle.home

import activitytest.example.com.home_moudle.home.api.API
import activitytest.example.com.home_moudle.home.bean.ArticleBean
import activitytest.example.com.home_moudle.home.bean.ConfigurationBean
import activitytest.example.com.network_moudle.datasource.RetrofitClient
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private lateinit var api: API


    companion object{

        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://49.232.6.89:8080/")
        }
        val homeRepository by lazy {
            HomeRepository()
        }
    }

    fun articleList():LiveData<ArticleBean>{
        api = retrofitClient?.create(API::class.java)!!
        val articleList = api.articleList()
        val mutableLiveData = MutableLiveData<ArticleBean>()
        articleList.enqueue(object :Callback<ArticleBean>{
            override fun onResponse(call: Call<ArticleBean>?, response: Response<ArticleBean>?) {
                response?.body()?.message?.let { Log.d("----------------------响应-------------------", it) }
                mutableLiveData.postValue(response?.body())
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ArticleBean>?, t: Throwable?) {
                mutableLiveData.postValue(ArticleBean(200, emptyList(),"网络错误"))
                Log.d("-----------网络错误-------------",t?.message+"")
            }

        })

        return mutableLiveData
    }

    fun configuration():LiveData<ConfigurationBean>{
        api = retrofitClient?.create(API::class.java)!!
        val configuration = api.configuration("pcd")
        val mutableLiveData = MutableLiveData<ConfigurationBean>()
        configuration.enqueue(object : Callback<ConfigurationBean>{
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<ConfigurationBean>?, response: Response<ConfigurationBean>?) {
                response?.body()?.message?.let { Log.d("---------------响应-------------------", it) }
                mutableLiveData.postValue(response?.body())
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ConfigurationBean>?, t: Throwable?) {
                mutableLiveData.postValue(ConfigurationBean())
                Log.d("-----------网络错误-------------",t?.message+"")
            }
        })

        return mutableLiveData

    }
}

