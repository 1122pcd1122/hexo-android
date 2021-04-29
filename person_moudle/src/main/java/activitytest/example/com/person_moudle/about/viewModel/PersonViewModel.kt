package activitytest.example.com.person_moudle.about.viewModel

import activitytest.example.com.person_moudle.about.bean.ArticleNum
import activitytest.example.com.person_moudle.about.bean.ConfigurationBean
import activitytest.example.com.person_moudle.about.bean.TgsNum
import activitytest.example.com.person_moudle.about.reposiory.PersonRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PersonViewModel:ViewModel() {
    private val personRepository = PersonRepository.personRepository

    fun configuration():LiveData<ConfigurationBean>{
        return personRepository.configuration()
    }

    fun articleNum():LiveData<ArticleNum>{
        return personRepository.articleNum()
    }
    fun tagNum():LiveData<TgsNum>{
        return personRepository.tagNum()
    }
}