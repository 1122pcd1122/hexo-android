package activitytest.example.com.login_module.service

import activitytest.example.com.base.service.CommonService
import activitytest.example.com.login_module.MainActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle

class LoginActivityService:CommonService {
    override fun startActivity(activity: Activity, bundle: Bundle?, message: String) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }
}