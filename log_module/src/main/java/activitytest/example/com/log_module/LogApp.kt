package activitytest.example.com.log_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.LogServiceFactory
import activitytest.example.com.componentbase.ServiceFactory
import activitytest.example.com.log_module.project.service.ModuleService
import android.app.Application

class LogApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        LogServiceFactory.setFragmentService(ModuleService())
    }

    override fun initModuleData(application: Application) {

    }
}