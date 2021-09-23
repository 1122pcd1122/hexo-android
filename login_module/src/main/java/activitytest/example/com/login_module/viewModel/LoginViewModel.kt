package activitytest.example.com.login_module.viewModel

import activitytest.example.com.login_module.LoginRepository
import activitytest.example.com.login_module.bean.User
import activitytest.example.com.login_module.util.TokenUtil.saveToken
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel: ViewModel() {

    private val isLoginSuccess = MutableLiveData<Boolean>()

    val loginState:LiveData<Boolean> = isLoginSuccess


    suspend fun login(user: User){
        val login = LoginRepository.login(user = user)
        if ( login.info?.message.equals("登录成功") ){
            val token = login.info?.token
            if (token != null) {
                saveToken(token)
            }
            isLoginSuccess.postValue(true)
        }else{
            isLoginSuccess.postValue(false)
        }
    }








}