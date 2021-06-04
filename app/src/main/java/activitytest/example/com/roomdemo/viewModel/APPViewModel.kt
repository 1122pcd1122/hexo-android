package activitytest.example.com.roomdemo.viewModel


import activitytest.example.com.network_module.ResultData
import activitytest.example.com.roomdemo.bean.UserInfo
import activitytest.example.com.roomdemo.repository.MainRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class APPViewModel : ViewModel() {

    init {
        Log.d("app_module","app模块--AppViewModel创建成功!")
    }

    private val mainRepository:MainRepository = MainRepository.mainRepository

    fun configuration():LiveData<ResultData<UserInfo>?>{
        return mainRepository.blogInfo()
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("app_module","app模块--AppViewModel销毁!")
    }
}