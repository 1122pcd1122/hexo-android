package activitytest.example.com.label_module.viewModel

import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.repository.LabelRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LabelsViewModel : ViewModel() {

    companion object {
        private val labelRepository = LabelRepository.labelRepository
    }

    init {
        listLabels()
    }

    private val _articles = MutableLiveData<List<Article>?>()

    val articles: LiveData<List<Article>?> = _articles

    fun listLabels(): LiveData<List<String>> {
        val mutableStateListOf = MutableLiveData<List<String>>()
        viewModelScope.launch {
            val listLabels = labelRepository.listLabels()
            val info = listLabels.info


            with(mutableStateListOf) {
                postValue(info)
            }

        }
        return mutableStateListOf
    }

    fun articles(label: String) {

        viewModelScope.launch {
            val articles = labelRepository.articles(label)
            val info = articles.info

            if (info != null) {
                _articles.postValue(info)
            } else {
                _articles.postValue(null)
            }
        }

    }

}