package activitytest.example.com.network_module

import activitytest.example.com.base.BaseApp
import android.app.Application
import android.content.Context
import com.google.gson.annotations.SerializedName


class NetWorkApp: BaseApp() {
    var context:Context? = null
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


    override fun initModuleApp(application: Application) {

    }

    override fun initModuleData(application: Application) {

    }
}