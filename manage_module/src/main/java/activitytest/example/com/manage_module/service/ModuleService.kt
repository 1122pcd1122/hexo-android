package activitytest.example.com.manage_module.service

import activitytest.example.com.componentbase.service.ManageModuleService
import activitytest.example.com.manage_module.PersonFragment
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ModuleService:ManageModuleService() {

    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val personFragment = PersonFragment()
        beginTransaction.add(containerId,personFragment,tag)
        beginTransaction.commit()
        return personFragment
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val personFragment = PersonFragment()
        beginTransaction.replace(containerId,personFragment,tag)
        beginTransaction.commit()
        return personFragment
    }
}