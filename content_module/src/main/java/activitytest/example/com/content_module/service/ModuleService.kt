package activitytest.example.com.content_module.service

import activitytest.example.com.componentbase.service.ContentModuleService
import activitytest.example.com.content_module.WebActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class ModuleService: ContentModuleService(){

    override fun startActivity(context: Context, activity: Activity, bundle: Bundle?, message: String) {
        val intent = Intent(context, WebActivity::class.java)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivity(intent)
    }
}