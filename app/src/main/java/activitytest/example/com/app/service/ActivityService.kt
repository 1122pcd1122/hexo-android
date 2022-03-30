package activitytest.example.com.app.service

import activitytest.example.com.app.activity.MainActivity
import activitytest.example.com.base.service.CommonService
import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ActivityService: CommonService {

    override fun startActivity(activity: Activity, bundle: Bundle?, message: String) {

        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }
}