package activitytest.example.com.manage_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.ManageServiceFactory
import activitytest.example.com.manage_module.service.ModuleService
import android.app.Application

class ManageApp :BaseApp(){

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleData(application: Application) {

    }

    override fun initModuleApp(application: Application) {
        ManageServiceFactory.setFragmentService(ModuleService())
    }
}