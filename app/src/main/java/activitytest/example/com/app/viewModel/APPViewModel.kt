package activitytest.example.com.app.viewModel


import activitytest.example.com.app.bean.UserData
import activitytest.example.com.app.bean.UserInfo
import activitytest.example.com.app.navigation.NavName
import activitytest.example.com.app.repository.MainRepository
import activitytest.example.com.base.ModuleNames
import activitytest.example.com.network_module.ResponseResult
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class APPViewModel : ViewModel() {

    init {
        Log.d("app_module","app模块--AppViewModel创建成功!")
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
            if (info!= null){
                mutableLiveData.postValue(info)
            }else{
                mutableLiveData.postValue(null)
            }
        }
        return mutableLiveData
    }

    fun navNameList(): LiveData<List<NavName>> {
        val menuList: MutableList<NavName> = ArrayList()
        menuList.add(NavName("主页", ModuleNames.home_module))
        menuList.add(NavName("标签", ModuleNames.label_module))
        menuList.add(NavName("日志", ModuleNames.log_module))
        menuList.add(NavName("我的", ModuleNames.manage_module))
        val mutableLiveData = MutableLiveData<List<NavName>>()
        mutableLiveData.postValue(menuList)
        return mutableLiveData
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("app_module","app模块--AppViewModel销毁!")
    }
}