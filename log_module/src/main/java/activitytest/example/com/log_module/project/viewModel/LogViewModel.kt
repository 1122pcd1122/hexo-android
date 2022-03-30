package activitytest.example.com.log_module.project.viewModel


import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.log_module.project.repository.LogRepository
import activitytest.example.com.network_module.ResponseResult
import androidx.lifecycle.*
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LogViewModel: ViewModel(){

    private val logRepository: LogRepository = LogRepository()

    fun log(): LiveData<List<ArticleByYears>> {
        val mutableLiveData = MutableLiveData<List<ArticleByYears>>()
        viewModelScope.launch {
           val log = logRepository.log()
            if (log.info != null){
                with(mutableLiveData) { postValue(log.info) }
            }
        }

        return mutableLiveData
    }


}


