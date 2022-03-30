package activitytest.example.com.app.viewModel


import activitytest.example.com.app.bean.UserData
import activitytest.example.com.app.repository.MainRepository
import activitytest.example.com.base.ModuleNames
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class APPViewModel : ViewModel() {

    init {
        Log.d("app_module", "app模块--AppViewModel创建成功!")
    }

    private val mainRepository: MainRepository = MainRepository.mainRepository

    /**
     * 博客信息
     */
    fun configuration(): LiveData<UserData?> {
        val mutableLiveData = MutableLiveData<UserData?>()
        viewModelScope.launch {
            val blogInfo = mainRepository.blogInfo()
            val info = blogInfo.info
            if (info != null) {
                mutableLiveData.postValue(info)
            } else {
                mutableLiveData.postValue(null)
            }
        }
        return mutableLiveData
    }




}