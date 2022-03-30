package activitytest.example.com.base.service

import android.app.Activity
import android.os.Bundle

interface CommonService {

    fun startActivity(activity: Activity, bundle: Bundle?, message:String) {}
}