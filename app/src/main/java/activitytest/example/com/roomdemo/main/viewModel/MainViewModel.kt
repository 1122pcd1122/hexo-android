package activitytest.example.com.roomdemo.main.viewModel


import activitytest.example.com.roomdemo.main.bean.ConfigurationBean
import activitytest.example.com.roomdemo.main.repository.MainRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mainRepository:MainRepository = MainRepository.mainRepository

    fun configuration():LiveData<ConfigurationBean>{
        return mainRepository.configuration()
    }


}