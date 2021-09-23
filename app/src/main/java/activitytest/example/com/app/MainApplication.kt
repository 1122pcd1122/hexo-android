package activitytest.example.com.app

import activitytest.example.com.base.AppConfig
import activitytest.example.com.base.BaseApp
import android.app.Application



class MainApplication : BaseApp(){
    
    override fun initModuleApp(application: Application) {
        AppConfig.moduleApps.forEach {
            try {
                val clazz = Class.forName(it)
                val baseApp : BaseApp = clazz.newInstance() as BaseApp
                baseApp.initModuleApp(this)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }

    override fun initModuleData(application: Application) {
        AppConfig.moduleApps.forEach {
            try {
                val clazz = Class.forName(it)
                val baseApp : BaseApp = clazz.newInstance() as BaseApp
                baseApp.initModuleData(this)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }


    override fun onCreate() {
        super.onCreate()

        initModuleApp(this)
        initModuleData(this)
    }




}