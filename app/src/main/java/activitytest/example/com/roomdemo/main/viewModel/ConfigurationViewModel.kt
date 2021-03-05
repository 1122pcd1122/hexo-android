package activitytest.example.com.roomdemo.main.viewModel

import activitytest.example.com.roomdemo.main.MainRepository
import activitytest.example.com.roomdemo.main.database.entity.Configuration
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ConfigurationViewModel : ViewModel() {
    private var configurationMutableLiveDataNet: LiveData<Configuration?>? = null
    private var configurationMutableLiveDataBd: LiveData<Configuration?>? = null
    var code: LiveData<Int?>? = null
    fun init() {
        Log.d(TAG, "创建仓库")
        val mainRepository: MainRepository? = MainRepository.instance
        if (mainRepository != null) {
            configurationMutableLiveDataNet = mainRepository.configuration
        }
        if (mainRepository != null) {
            configurationMutableLiveDataBd = mainRepository.configurationFromDB
        }
        if (mainRepository != null) {
            code = mainRepository.code
        }
    }

    val configurationNet: LiveData<Configuration?>?
        get() {
            Log.d(TAG, "网络获取")
            return configurationMutableLiveDataNet
        }
    val configurationDb: LiveData<Configuration?>?
        get() {
            Log.d(TAG, "数据库获取")
            return configurationMutableLiveDataBd
        }

    @JvmName("getCode1")
    fun getCode(): LiveData<Int?>? {
        Log.d(TAG, "获取状态码")
        return code
    }

    companion object {
        private val TAG = ConfigurationViewModel::class.java.name
    }
}