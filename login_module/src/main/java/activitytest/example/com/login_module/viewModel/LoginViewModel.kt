package activitytest.example.com.login_module.viewModel

import activitytest.example.com.base.ModuleNames
import activitytest.example.com.login_module.LoginRepository
import activitytest.example.com.login_module.bean.User
import activitytest.example.com.login_module.screen.LoginState
import activitytest.example.com.base.util.TokenUtil.saveToken
import activitytest.example.com.login_module.bean.RegisterInform
import activitytest.example.com.login_module.screen.RegisterState
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {

    private val isLoginSuccess = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = isLoginSuccess


    @RequiresApi(Build.VERSION_CODES.R)
    suspend fun login(user: User) {
        //初始为false
        isLoginSuccess.postValue(LoginState.Start)

        val login = LoginRepository.login(user = user)
        val lResponse = login.info
        Log.d(ModuleNames.login_module,
            "状态码:${lResponse?.statueCode}  信息:${lResponse?.statueStr}  登录时间:${lResponse?.loginDate}")
        if (lResponse?.statueCode == 5) {
            //字符串
            val token = lResponse.token
            //保持token
            if (token != null) {
                saveToken(token)
            }
            //登录成功
            isLoginSuccess.postValue(LoginState.SUCCESS)
        } else {
            //登录失败
            isLoginSuccess.postValue(LoginState.ERROR)
        }
    }

    private val isRegisterSuccess = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> = isRegisterSuccess


    @RequiresApi(Build.VERSION_CODES.R)
    suspend fun register(registerInform: RegisterInform,confirmPassWord:String) {
        isRegisterSuccess.postValue(RegisterState.Start.also {
            it.message = "开始注册"
        })

        //判断用户名是否存在
        if (LoginRepository.isContainName(registerInform.username).info == true){
            isRegisterSuccess.postValue(RegisterState.ERROR.also {
                it.message = "用户名存在"
            })
            return
        }

        //判断密码两次密码是否相同
        if (registerInform.password != confirmPassWord){
            isRegisterSuccess.postValue(RegisterState.ERROR.also {
                it.message = "两次密码重复"
            })
            return
        }

        //判断是否包含仓库名
        if (LoginRepository.isContainRepository(repositoryName = registerInform.repositoryName).info == true){
            isRegisterSuccess.postValue(RegisterState.ERROR.also {
                it.message = "仓库名存在"
            })
            return
        }

        //注册
        val registerInfo = LoginRepository.register(registerInform = registerInform)
        val info = registerInfo.info
        if (info?.statueCode == 15) {
            //注册成功
            //获取用户信息
            val token = info.token
            saveToken(token)

            isRegisterSuccess.postValue(RegisterState.SUCCESS.also {
                it.message = "注册成功"
            })
        } else {
            //注册失败
            isRegisterSuccess.postValue(RegisterState.ERROR.also {
                it.message = "服务器问题"
            })
        }

    }

}