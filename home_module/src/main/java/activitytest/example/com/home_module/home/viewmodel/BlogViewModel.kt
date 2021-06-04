package activitytest.example.com.home_module.home.viewmodel

import activitytest.example.com.home_module.home.HomeRepository
import activitytest.example.com.home_module.home.bean.ListArticleInfo
import activitytest.example.com.home_module.home.bean.UserInfo
import activitytest.example.com.network_module.ResultData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class BlogViewModel : ViewModel() {

    private var homeRepository:HomeRepository = HomeRepository.homeRepository


    fun configuration(): LiveData<ResultData<UserInfo>?> {
        Log.d("home_module","ViewModel---获取成功用户信息")

        return homeRepository.configuration()
    }

    fun articleList():LiveData<ResultData<ListArticleInfo>?>{
        Log.d("home_module","ViewModel---获取成功文章")
        return homeRepository.articleList()

    }

}