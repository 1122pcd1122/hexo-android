package activitytest.example.com.login_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.service.ActivityServiceFactory
import activitytest.example.com.login_module.service.LoginActivityService
import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class LoginApp: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {

        ActivityServiceFactory.setService(MyRouteTable.loginModule_MainActivity,LoginActivityService())
    }

    override fun initModuleData(application: Application) {

    }
}