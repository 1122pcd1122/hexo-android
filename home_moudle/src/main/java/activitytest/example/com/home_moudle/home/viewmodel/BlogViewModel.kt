package activitytest.example.com.home_moudle.home.viewmodel

import activitytest.example.com.home_moudle.home.HomeRepository
import activitytest.example.com.home_moudle.home.bean.ArticleBean
import activitytest.example.com.home_moudle.home.bean.ConfigurationBean
import activitytest.example.com.home_moudle.home.lifecycle.HomeLifeCycle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class BlogViewModel : ViewModel() {

    private var homeRepository: HomeRepository = HomeRepository.homeRepository


    fun configuration(): LiveData<ConfigurationBean> {
        Log.d(TAG,"ViewModel---获取成功用户信息")
        return homeRepository.configuration()
    }

    fun articleList():LiveData<ArticleBean>{
        Log.d(TAG,"ViewModel---获取成功文章列表")
        return homeRepository.articleList()
    }

    companion object {
        private val TAG = HomeLifeCycle::class.java.name
    }
}