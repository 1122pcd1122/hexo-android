package activitytest.example.com.componentbase.service

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface CommonService {
    /**
     * 创建Fragment
     * @param activity
     * @param containerId
     * @param manager
     * @param bundle
     * @param tag
     * @return
     */
    fun newFragment(activity: Activity, containerId:Int, manager: FragmentManager, bundle: Bundle?, tag:String): Fragment?

    fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String):Fragment?

    fun startActivity(context: Context,activity: Activity, bundle: Bundle?, message: String)
}