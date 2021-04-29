package activitytest.example.com.roomdemo.main.repository

import activitytest.example.com.network_moudle.datasource.RetrofitClient
import activitytest.example.com.roomdemo.main.bean.ConfigurationBean

import activitytest.example.com.roomdemo.main.api.API
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainRepository {

    companion object{

        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://49.232.6.89:8080/")
        }
        val mainRepository by lazy {
            MainRepository()
        }
    }

    fun configuration():LiveData<ConfigurationBean>{
        val create = retrofitClient?.create(API::class.java)
        val configuration = create?.configuration("pcd")
        val mutableLiveData = MutableLiveData<ConfigurationBean>()
        configuration?.enqueue(object : Callback<ConfigurationBean>{
            override fun onResponse(call: Call<ConfigurationBean>?, response: Response<ConfigurationBean>?) {
                mutableLiveData.postValue(response?.body())
            }

            override fun onFailure(call: Call<ConfigurationBean>?, t: Throwable?) {
               mutableLiveData.postValue(ConfigurationBean())
            }

        })

        return mutableLiveData
    }



}



