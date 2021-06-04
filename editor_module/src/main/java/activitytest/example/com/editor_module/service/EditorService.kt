package activitytest.example.com.editor_module.service

import activitytest.example.com.componentbase.service.EditorModuleService
import activitytest.example.com.editor_module.EditorFragment
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class EditorService:EditorModuleService() {
    override fun newFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {

        val beginTransaction = manager.beginTransaction()
        val editorFragment = EditorFragment()
        beginTransaction.add(containerId,editorFragment)
        beginTransaction.commit()
        return editorFragment
    }

    override fun replaceFragment(activity: Activity, containerId: Int, manager: FragmentManager, bundle: Bundle?, tag: String): Fragment? {
        val beginTransaction = manager.beginTransaction()
        val editorFragment = EditorFragment()
        beginTransaction.replace(containerId,editorFragment)
        beginTransaction.commit()
        return editorFragment
    }


}