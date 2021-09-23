package activitytest.example.com.label_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.LabelModuleFactory
import activitytest.example.com.label_module.service.ModuleService
import android.app.Application

class FriendApp:BaseApp() {
    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        LabelModuleFactory.setFragmentService(ModuleService())
    }

    override fun initModuleData(application: Application) {

    }
}