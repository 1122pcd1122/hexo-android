package activitytest.example.com.log_module.project.viewModel


import activitytest.example.com.log_module.project.bean.ArticleByYears
import activitytest.example.com.log_module.project.repository.LogRepository
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope

class LogViewModel: ViewModel(){

    private val logRepository: LogRepository = LogRepository()

    fun log(): LiveData<PagingData<ArticleByYears>> {
        return logRepository.log().asLiveData()
    }


}


