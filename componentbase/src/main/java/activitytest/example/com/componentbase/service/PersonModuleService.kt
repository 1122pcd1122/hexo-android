package activitytest.example.com.componentbase.service

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class PersonModuleService:CommonService {
    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        return null
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        return null
    }

    override fun startActivity(context: Context, activity: Activity, bundle: Bundle?, message: String) {
        TODO("Not yet implemented")
    }
}