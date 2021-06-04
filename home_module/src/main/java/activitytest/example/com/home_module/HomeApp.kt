package activitytest.example.com.home_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.HomeServiceFactory
import activitytest.example.com.home_module.home.service.ModuleService
import android.app.Application

class HomeApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        HomeServiceFactory.setFragmentService(ModuleService())
    }

    override fun initModuleData(application: Application) {

    }
}