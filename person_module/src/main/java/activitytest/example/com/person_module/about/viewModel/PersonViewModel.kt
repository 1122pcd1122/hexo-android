package activitytest.example.com.person_module.about.viewModel

import activitytest.example.com.network_module.ResultData
import activitytest.example.com.person_module.about.bean.ArticleNum
import activitytest.example.com.person_module.about.bean.LabelNum
import activitytest.example.com.person_module.about.bean.UserInfo
import activitytest.example.com.person_module.about.reposiory.PersonRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PersonViewModel:ViewModel() {
    private val personRepository = PersonRepository.personRepository

    fun userInfo():LiveData<ResultData<UserInfo>?>{
        return personRepository.userInfo()
    }

    fun articleNum():LiveData<ResultData<ArticleNum>?>{
        return personRepository.articleNum()
    }
    fun tagNum():LiveData<ResultData<LabelNum>?>{
        return personRepository.tagNum()
    }
}