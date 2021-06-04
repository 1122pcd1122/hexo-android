package activitytest.example.com.editor_module

import activitytest.example.com.base.BaseApp
import activitytest.example.com.componentbase.EditorServiceFactory
import activitytest.example.com.editor_module.service.EditorService
import android.app.Application

class EditorApp:BaseApp() {

    override fun onCreate() {
        super.onCreate()

        initModuleApp(this)
        initModuleData(this)
    }
    override fun initModuleApp(application: Application) {
        EditorServiceFactory.setFragmentService(EditorService())
    }

    override fun initModuleData(application: Application) {

    }
}