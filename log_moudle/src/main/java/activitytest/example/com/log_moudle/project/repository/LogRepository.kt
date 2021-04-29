package activitytest.example.com.log_moudle.project.repository

import activitytest.example.com.log_moudle.project.api.API
import activitytest.example.com.log_moudle.project.bean.ArticlesByYear
import activitytest.example.com.network_moudle.datasource.RetrofitClient
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LogRepository {

    companion object{
        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://49.232.6.89:8080/")
        }
        val homeRepository by lazy {
            LogRepository()
        }
    }


    fun listBlogByYear():LiveData<ArticlesByYear>{
        val create = retrofitClient?.create(API::class.java)
        val mutableLiveData = MutableLiveData<ArticlesByYear>()
        create?.listBlogByYear()?.enqueue(object :Callback<ArticlesByYear>{
            override fun onResponse(call: Call<ArticlesByYear>?, response: Response<ArticlesByYear>?) {
                Log.d("--------获取成功----------",response?.message()!!)
                mutableLiveData.postValue(response.body())
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ArticlesByYear>?, t: Throwable?) {
                Log.d("----------获取失败----------",t?.message+"")
                mutableLiveData.postValue(ArticlesByYear())
            }
        })

        return mutableLiveData
    }

}