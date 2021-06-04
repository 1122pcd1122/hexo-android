package activitytest.example.com.log_module.project.service

import activitytest.example.com.componentbase.service.LogModuleService
import activitytest.example.com.log_module.project.LogFragment
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ModuleService:LogModuleService() {
    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment {

        val beginTransaction = manager.beginTransaction()
        val logFragment = LogFragment()
        beginTransaction.add(containerId,logFragment,tag)
        beginTransaction.commit()
        return logFragment
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val logFragment = LogFragment()
        beginTransaction.replace(containerId,logFragment,tag)
        beginTransaction.commit()
        return logFragment
    }
}