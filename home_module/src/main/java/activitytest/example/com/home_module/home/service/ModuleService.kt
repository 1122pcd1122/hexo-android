package activitytest.example.com.home_module.home.service

import activitytest.example.com.componentbase.service.HomeModuleService
import activitytest.example.com.home_module.home.HomeFragment
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class ModuleService: HomeModuleService() {
    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment {

        val beginTransaction = manager.beginTransaction()
        val homeFragment = HomeFragment()
        beginTransaction.add(containerId,homeFragment,tag)
        beginTransaction.commit()
        return homeFragment
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val homeFragment = HomeFragment()
        beginTransaction.replace(containerId,homeFragment,tag)
        beginTransaction.commit()
        return homeFragment
    }
}