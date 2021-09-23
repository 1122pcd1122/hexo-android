package activitytest.example.com.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseApp :Application(){

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }

    abstract fun initModuleApp(application: Application)

    abstract fun initModuleData(application: Application)

}