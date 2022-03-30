package activitytest.example.com.categories_module

import activitytest.example.com.categories_module.bean.Article
import activitytest.example.com.categories_module.repository.CategoriesRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class CategoriesViewModel: ViewModel() {

    companion object {
        private val categoriesRepository = CategoriesRepository.categoriesRepository
    }

    private val _currentCategories:MutableLiveData<String> = MutableLiveData()
    val currentCategories:LiveData<String> = _currentCategories


    fun updateCurrentCategories(categories: String){
        _currentCategories.postValue(categories)
    }

    fun categoriesNum(): LiveData<Int?> {
        val mutableLiveData = MutableLiveData<Int?>()
        viewModelScope.launch {
            val categoriesNum = categoriesRepository.categoriesNum()
            val info = categoriesNum.info
            if (info != null){
                with(mutableLiveData) { postValue(info) }
            }else{
                mutableLiveData.postValue(null)
            }
        }
        return mutableLiveData
    }

    fun categories():LiveData<List<String>?>{
        val mutableLiveData = MutableLiveData<List<String>?>()
        viewModelScope.launch {
            val categories = categoriesRepository.categories()
            val info = categories.info
            if (info != null){
                with(mutableLiveData) { postValue(info) }
            }else{
                mutableLiveData.postValue(null)
            }
        }
        return mutableLiveData
    }

    fun articleByCategories(categories:String):LiveData<List<Article>?>{
        val mutableLiveData = MutableLiveData<List<Article>?>()
        viewModelScope.launch {
            val articleByCategories =
                categoriesRepository.articleByCategories(categories = categories)
            val info = articleByCategories.info
            if (info != null){
                with(mutableLiveData) { postValue(info) }
            }else{
                mutableLiveData.postValue(null)
            }
        }

        return mutableLiveData
    }
}