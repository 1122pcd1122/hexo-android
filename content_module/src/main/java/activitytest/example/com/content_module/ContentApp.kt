package activitytest.example.com.content_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.ContentServiceFactory
import activitytest.example.com.content_module.service.ModuleService
import android.app.Application

class ContentApp:BaseApp() {

    override fun onCreate() {
        super.onCreate()

        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        ContentServiceFactory.setFragmentService(ModuleService())
    }

    override fun initModuleData(application: Application) {

    }
}