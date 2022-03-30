package activitytest.example.com.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseApp :Application(){

    companion object{
        @SuppressLint("StaticFieldLeak")
        var context:Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)

        context = baseContext
    }

    abstract fun initModuleApp(application: Application)

    abstract fun initModuleData(application: Application)

}