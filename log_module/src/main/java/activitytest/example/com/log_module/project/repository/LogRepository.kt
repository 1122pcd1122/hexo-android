package activitytest.example.com.log_module.project.repository

import activitytest.example.com.network_module.datasource.RetrofitClient

class LogRepository {

    companion object{
        //retrofit客户端
        val retrofitClient by lazy {
            RetrofitClient.createDefaultRetrofitClient("http://192.168.43.196:8080/")
        }
        val homeRepository by lazy {
            LogRepository()
        }
    }




}