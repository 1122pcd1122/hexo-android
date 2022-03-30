package activitytest.example.com.home_module.home.viewmodel

import activitytest.example.com.home_module.home.HomeRepository
import activitytest.example.com.home_module.home.bean.Article
import activitytest.example.com.home_module.home.bean.UserData
import androidx.lifecycle.viewModelScope
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.launch


class BlogViewModel : ViewModel() {

    private var homeRepository:HomeRepository = HomeRepository.homeRepository

    //刷新
    private val _isRefreshIng:MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefreshIng:LiveData<Boolean> = _isRefreshIng

    //当前页
    private val _currentPage:MutableLiveData<Int> = MutableLiveData(1)
    val currentPage:LiveData<Int> = _currentPage

    //签名
    private val _signature:MutableLiveData<String> = MutableLiveData()
    val signature:LiveData<String> = _signature

    //页数
    private val _pageNum:MutableLiveData<Int> = MutableLiveData()
    val pageNum:LiveData<Int> = _pageNum


    init {
        configuration()
        pageNum()
    }

    /**
     * 刷新
     */
    fun refresh(){
        _isRefreshIng.postValue(true)

        configuration()
        pageNum()
        _currentPage.postValue(1)
        articleByPage(_currentPage.value!!)

        _isRefreshIng.postValue(false)
    }



    /**
     * 博客信息
     */
    private fun configuration() {
        viewModelScope.launch {
            val blogInfo = homeRepository.configuration()
            val info = blogInfo.info
            if (info != null){



                _signature.postValue(info.signature)
            }
        }
    }

    /**
     * 页面数
     */
    private fun pageNum(){
        viewModelScope.launch {
            val pageNum = homeRepository.pageNum()
            val info = pageNum.info
            if (info != null){
                with(_pageNum) { postValue(info) }
            }
        }
    }

    /**
     * 通过页数获取文章
     */
    fun articleByPage(page:Int): LiveData<List<Article>> {
        val mutableLiveData = MutableLiveData<List<Article>>()
        viewModelScope.launch {
            val articleByPage = homeRepository.articleByPage(page = page)
            val info = articleByPage.info
            if (info != null){
                with(mutableLiveData) {postValue(info)}
            }
        }

        return mutableLiveData
    }

    /**
     * 更新当前页
     */
    fun updateCurrentPage(page: Int) {
        _currentPage.postValue(page)
    }

}