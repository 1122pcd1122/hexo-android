package activitytest.example.com.friend_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.FriendModuleFactory
import activitytest.example.com.friend_module.service.ModuleService
import android.app.Application

class FriendApp:BaseApp() {
    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        FriendModuleFactory.setFragmentService(ModuleService())
    }

    override fun initModuleData(application: Application) {

    }
}