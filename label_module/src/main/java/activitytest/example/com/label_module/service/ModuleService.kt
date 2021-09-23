package activitytest.example.com.label_module.service

import activitytest.example.com.componentbase.service.LabelModuleService
import activitytest.example.com.label_module.FriendFragment
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ModuleService:LabelModuleService() {
    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val editorFragment = FriendFragment()
        beginTransaction.add(containerId,editorFragment)
        beginTransaction.commit()
        return editorFragment
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val editorFragment = FriendFragment()
        beginTransaction.replace(containerId,editorFragment)
        beginTransaction.commit()
        return editorFragment
    }


}