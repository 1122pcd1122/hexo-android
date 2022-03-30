package activitytest.example.com.app


import activitytest.example.com.app.service.ActivityService
import activitytest.example.com.base.AppConfig
import activitytest.example.com.base.BaseApp
import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.service.ActivityServiceFactory
import android.app.Application

class MainApplication : BaseApp(){
    

    override fun onCreate() {
        super.onCreate()

        initModuleApp(this)
        initModuleData(this)
    }

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

        ActivityServiceFactory.setService(MyRouteTable.app_MainActivity,ActivityService())
    }

    override fun initModuleData(application: Application) {

    }


}