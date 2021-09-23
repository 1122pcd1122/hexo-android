package activitytest.example.com.login_module

import activitytest.example.com.base.BaseApp
import android.app.Application

class LoginApp : BaseApp(){

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleData(application: Application) {

    }

    override fun initModuleApp(application: Application) {

    }


}