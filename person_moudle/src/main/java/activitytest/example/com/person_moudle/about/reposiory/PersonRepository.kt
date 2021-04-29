package activitytest.example.com.person_moudle.about.reposiory

import activitytest.example.com.network_moudle.datasource.RetrofitClient
import activitytest.example.com.person_moudle.about.api.API
import activitytest.example.com.person_moudle.about.bean.ArticleNum
import activitytest.example.com.person_moudle.about.bean.ConfigurationBean
import activitytest.example.com.person_moudle.about.bean.TgsNum
import android.annotation.SuppressLint
import android.support.v4.app.INotificationSideChannel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {
    private lateinit var api: API


    companion object{

        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://49.232.6.89:8080/")
        }
        val personRepository by lazy {
            PersonRepository()
        }
    }

    fun configuration(): LiveData<ConfigurationBean> {
        val create = retrofitClient?.create(API::class.java)
        val configuration = create?.configuration("pcd")
        val mutableLiveData = MutableLiveData<ConfigurationBean>()
        configuration?.enqueue(object :Callback<ConfigurationBean>{
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<ConfigurationBean>?, response: Response<ConfigurationBean>?) {
                response?.body()?.message?.let { Log.d("----------------asdhfksajdf-----------------", it) }
                mutableLiveData.postValue(response?.body())
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ConfigurationBean>?, t: Throwable?) {
                Log.d("a-------------------------------------",t?.message+"")
                mutableLiveData.postValue(ConfigurationBean())
            }

        })

        return mutableLiveData
    }

    fun articleNum():LiveData<ArticleNum>{
        val create = retrofitClient?.create(API::class.java)
        val mutableLiveData = MutableLiveData<ArticleNum>()
        create?.articleNum()?.enqueue(object :Callback<ArticleNum>{
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ArticleNum>?, t: Throwable?) {
                mutableLiveData.postValue(ArticleNum())
                Log.d("a-------------------------------------",t?.message+"")
            }

            override fun onResponse(call: Call<ArticleNum>?, response: Response<ArticleNum>?) {
                mutableLiveData.postValue(response?.body())
            }
        })

        return mutableLiveData
    }

    fun tagNum():LiveData<TgsNum>{
        val create = retrofitClient?.create(API::class.java)
        val mutableLiveData = MutableLiveData<TgsNum>()
        create?.tagsNum()?.enqueue(object :Callback<TgsNum>{
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<TgsNum>?, t: Throwable?) {
                mutableLiveData.postValue(TgsNum())
                Log.d("a-------------------------------------",t?.message+"")
            }

            override fun onResponse(call: Call<TgsNum>?, response: Response<TgsNum>?) {
                mutableLiveData.postValue(response?.body())
            }
        })

        return mutableLiveData
    }
}