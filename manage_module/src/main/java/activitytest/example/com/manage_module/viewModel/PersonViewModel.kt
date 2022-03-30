package activitytest.example.com.manage_module.viewModel

import activitytest.example.com.manage_module.bean.UpdateConfig
import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.manage_module.repository.PersonRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel:ViewModel() {
    private val personRepository = PersonRepository.personRepository

    fun userInfo(): LiveData<UserData?> {
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

    fun articleNum(): LiveData<Int?> {
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
    fun tagNum(): LiveData<Int?> {
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

    fun changeBlogName(blogName: UpdateConfig):LiveData<String> {
        val blogNameLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val changeBlogName = personRepository.changeBlogName(blogName = blogName)
            blogNameLiveData.postValue(changeBlogName.info!!)
        }

        return blogNameLiveData
    }

    fun changeSignature(signature:UpdateConfig): LiveData<String> {
        val signatureLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val changeSignature = personRepository.changeSignature(signature)
            signatureLiveData.postValue(changeSignature.info ?: "")
        }
        return signatureLiveData
    }

    fun changeLocation(location:UpdateConfig):LiveData<String>{
        val locationLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val changeLocation = personRepository.changeLocation(location = location)
            locationLiveData.postValue(changeLocation.info ?: "")
        }
        return locationLiveData
    }

    fun changeEmail(email:UpdateConfig):LiveData<String>{
        val emailLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val changeEmail = personRepository.changeEmail(email = email)
            emailLiveData.postValue(changeEmail.info ?: "")
        }
        return emailLiveData
    }
}