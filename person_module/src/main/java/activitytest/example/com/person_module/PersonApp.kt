package activitytest.example.com.person_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.PersonServiceFactory
import activitytest.example.com.person_module.about.service.ModuleService
import android.app.Application

class PersonApp :BaseApp(){

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleData(application: Application) {

    }

    override fun initModuleApp(application: Application) {
        PersonServiceFactory.setFragmentService(ModuleService())
    }
}