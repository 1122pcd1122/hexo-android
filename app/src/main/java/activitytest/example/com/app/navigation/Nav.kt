package activitytest.example.com.app.navigation

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.ModuleNames
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import java.util.ArrayList

/**
 * 模块导航
 */
class Nav(private val activity: AppCompatActivity, private val bundle: Bundle?) {

    companion object {

        fun defaultNavNameList():List<NavName>{
            val menuList: MutableList<NavName> = ArrayList()
            menuList.add(NavName("主页", ModuleNames.home_module))
            menuList.add(NavName("标签", ModuleNames.label_module))
            menuList.add(NavName("日志", ModuleNames.log_module))
            menuList.add(NavName("我的", ModuleNames.manage_module))
            return menuList
        }
    }

    /**
     * 更新导航名
     */
    fun updateNavName(listNavName: List<NavName>) {
        listNavName.forEach {
            when {
                it.moduleName?.contentEquals(ModuleNames.home_module) == true -> {
                    updateHomeName(it.name)
                }
                it.moduleName?.contentEquals(ModuleNames.label_module) == true -> {
                    updateLabelName(it.name)
                }
                it.moduleName?.contentEquals(ModuleNames.log_module) == true -> {
                    updateLogName(it.name)
                }
                it.moduleName?.contentEquals(ModuleNames.manage_module) == true -> {
                    updateManageName(it.name)
                }
                it.moduleName?.contentEquals(ModuleNames.content_module) == true -> {
                    updateContentName(it.name)
                }
            }
        }
    }

    /**
     * 更新主页导航名
     */
    private fun updateHomeName(name: String?) {
        EnumNav.HOME.changeName(name)
    }


    /**
     * 更新日志模块导航名
     */
    private fun updateLogName(name: String?) {
        EnumNav.LOG.changeName(name)
    }

    /**
     * 更新标签名
     */
    private fun updateLabelName(name: String?) {
        EnumNav.LABEL.changeName(name)
    }

    /**
     * 更新内容模块导航名
     */
    private fun updateContentName(name: String?) {
        EnumNav.CONTENT.changeName(name)
    }



    /**
     * 更改个人主页导航名
     */
    private fun updateManageName(name: String?) {
        EnumNav.MANAGE.changeName(name)
    }


    /**
     * 根据导航页名称导航
     */
    fun nav(name: String?):Fragment? {
        when {
            name?.let { EnumNav.HOME.getNavName().contentEquals(it) } == true -> {
                Log.d("app_module", "导航->homeFragment")
//                HomeServiceFactory.getFragmentService()?.replaceFragment(activity = activity, containerId = R.id.fragment_container, activity.supportFragmentManager, bundle, "HomeFragment")
                return ARouter.getInstance().build(MyRouteTable.homeModule_HomeFragment).navigation() as Fragment
            }

            name?.let { EnumNav.MANAGE.getNavName().contentEquals(it) } == true -> {
                Log.d("app_module", "导航->personFragment")
//                ManageServiceFactory.getFragmentService()?.replaceFragment(activity = activity, R.id.fragment_container, activity.supportFragmentManager, bundle, "ManageFragment")
                return ARouter.getInstance().build(MyRouteTable.manageModule_PersonFragment).navigation() as Fragment
            }
            name?.let { EnumNav.LABEL.getNavName().contentEquals(it) } == true -> {
                Log.d("app_module", "导航->labelFragment")
//                LabelModuleFactory.getFragmentService()?.replaceFragment(activity = activity, R.id.fragment_container, activity.supportFragmentManager, bundle, "labelFragment")
                return ARouter.getInstance().build(MyRouteTable.labelModule_LabelFragment).navigation() as Fragment
            }
            name?.let { EnumNav.LOG.getNavName().contentEquals(it) } == true -> {
                Log.d("app_module", "导航->logFragment")
//                LogServiceFactory.getFragmentService()?.replaceFragment(activity = activity, R.id.fragment_container, activity.supportFragmentManager, bundle, "LogFragment")
                return ARouter.getInstance().build(MyRouteTable.logModule_LogFragment).navigation() as Fragment
            }

            name?.let { EnumNav.CONTENT.getNavName().contentEquals(it) } == true -> {
                Log.d("app_module", "导航->ContentActivity")
//                ContentServiceFactory.getFragmentService()?.startActivity(activity.baseContext, activity, bundle, "进入WebActivity")
                return ARouter.getInstance().build(MyRouteTable.webActivityModule_MainActivity).navigation() as Fragment
            }

            else -> {
                Log.d("app_module", "内容错误")
                return null
            }
        }
    }



}