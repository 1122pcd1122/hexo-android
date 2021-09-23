package activitytest.example.com.home_module.home.viewmodel

import activitytest.example.com.home_module.home.HomeRepository
import activitytest.example.com.home_module.home.bean.ListArticle
import activitytest.example.com.home_module.home.bean.UserData
import androidx.lifecycle.viewModelScope
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData

import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class BlogViewModel : ViewModel() {

    private var homeRepository:HomeRepository = HomeRepository.homeRepository


    /**
     * 博客信息
     */
    fun configuration(): LiveData<UserData> {
        val mutableLiveData = MutableLiveData<UserData>()
        viewModelScope.launch {
            val blogInfo = homeRepository.configuration()
            val info = blogInfo.info
            if (info != null){
                mutableLiveData.postValue(info)
            }
        }
        return mutableLiveData
    }

    fun articleList(): Flow<PagingData<ListArticle>> {
        Log.d("home_module","ViewModel---获取成功文章")
        return homeRepository.articleList().cachedIn(viewModelScope)

    }

}