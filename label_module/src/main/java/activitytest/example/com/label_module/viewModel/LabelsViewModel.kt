package activitytest.example.com.label_module.viewModel

import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.repository.LabelRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LabelsViewModel:ViewModel() {

    companion object{
        private val labelRepository = LabelRepository.labelRepository
    }

    fun listLabels(): LiveData<List<String>?> {
        val labelsLiveData = MutableLiveData<List<String>?>()
        viewModelScope.launch {
            val listLabels = labelRepository.listLabels()
            val info = listLabels.info

            if (info != null){
                labelsLiveData.postValue(info)
            }else{
                labelsLiveData.postValue(null)
            }
        }
       return labelsLiveData
    }

    fun articles(label:String):LiveData<List<Article>?>{
        val articleLiveData = MutableLiveData<List<Article>?>()
        viewModelScope.launch {
            val articles = labelRepository.articles(label)
            val info = articles.info

            if (info != null){
                articleLiveData.postValue(info)
            }else{
                articleLiveData.postValue(null)
            }
        }
        return articleLiveData
    }

}