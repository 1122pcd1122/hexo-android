package activitytest.example.com.manage_module.viewModel

import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.manage_module.repository.PersonRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel:ViewModel() {
    private val personRepository = PersonRepository.personRepository

    fun userInfo(): MutableLiveData<UserData?> {
        val userDataLiveData = MutableLiveData<UserData?>()
        viewModelScope.launch {
            val userInfo = personRepository.userInfo()
            val info = userInfo.info
            if (info != null){
                userDataLiveData.postValue(info)
            }else{
                userDataLiveData.postValue(null)
            }
        }
        return userDataLiveData
    }

    fun articleNum(): MutableLiveData<Int?> {
        val articleNumLiveData = MutableLiveData<Int?>()
        viewModelScope.launch {
            val articleNum = personRepository.articleNum()
            val info = articleNum.info
            if (info != null){
                articleNumLiveData.postValue(info)
            }else{
                articleNumLiveData.postValue(null)
            }
        }
        return articleNumLiveData
    }
    fun tagNum(): MutableLiveData<Int?> {
        val tagNumLiveData = MutableLiveData<Int?>()
        viewModelScope.launch {
            val tagNum = personRepository.tagNum()
            val info = tagNum.info
            if (info != null){
                tagNumLiveData.postValue(info)
            }else{
                tagNumLiveData.postValue(null)
            }
        }
        return tagNumLiveData
    }
}