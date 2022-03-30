package activitytest.example.com.app.screen.ui

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.arouterInterceptor.LoginNavigationCallbackImpl
import activitytest.example.com.categories_module.CategoriesFragment
import activitytest.example.com.home_module.home.HomeFragment
import activitytest.example.com.label_module.FriendFragment
import activitytest.example.com.log_module.project.LogFragment
import activitytest.example.com.manage_module.PersonFragment
import activitytest.example.com.roomdemo.R
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import coil.annotation.ExperimentalCoilApi
import com.alibaba.android.arouter.launcher.ARouter

sealed class Screen(val id:String, val title:String, val icon: ImageVector) {
    object Home:Screen(MyRouteTable.homeModule_HomeFragment,"主页",Icons.Outlined.Home)
    object Tag:Screen(MyRouteTable.labelModule_LabelFragment,"标签",Icons.Outlined.Tag)
    object Log:Screen(MyRouteTable.logModule_LogFragment,"日志",Icons.Outlined.Label)
    object Categories:Screen(MyRouteTable.categoriesModule_CategoriesFragment,"分类",Icons.Outlined.Category)
    object Manage:Screen(MyRouteTable.manageModule_PersonFragment,"我的",Icons.Outlined.Person)



    object Screens{

        private var activity: AppCompatActivity? = null

        @JvmName("setActivity")
        fun setActivity(activity: AppCompatActivity){
            this.activity = activity
        }

        val listScreen = mutableListOf<Screen>().also {
            it.add(Home)
            it.add(Tag)
            it.add(Log)
            it.add(Categories)
        }

        @ExperimentalCoilApi
        fun navToFragment(screen: Screen){
            val supportFragmentManager = activity?.supportFragmentManager
            val beginTransaction = supportFragmentManager?.beginTransaction()
            when(screen){
                is Home -> {
                    val navigation: HomeFragment =
                        ARouter.getInstance().build(MyRouteTable.homeModule_HomeFragment).navigation(
                            activity,
                            LoginNavigationCallbackImpl()) as HomeFragment
                    beginTransaction?.replace(R.id.fragment_container, navigation)
                    beginTransaction?.commit()
                }
                is Tag -> {
                    val navigation: FriendFragment =
                        ARouter.getInstance().build(MyRouteTable.labelModule_LabelFragment).navigation(
                            activity,
                            LoginNavigationCallbackImpl()) as FriendFragment
                    beginTransaction?.replace(R.id.fragment_container, navigation)
                    beginTransaction?.commit()
                }
                is Log -> {
                    val navigation: LogFragment =
                        ARouter.getInstance().build(MyRouteTable.logModule_LogFragment).navigation(
                            activity,
                            LoginNavigationCallbackImpl()) as LogFragment
                    beginTransaction?.replace(R.id.fragment_container, navigation)
                    beginTransaction?.commit()
                }
                is Manage -> {
                    val navigation: PersonFragment =
                        ARouter.getInstance().build(MyRouteTable.manageModule_PersonFragment).navigation(
                            activity,
                            LoginNavigationCallbackImpl()) as PersonFragment
                    beginTransaction?.replace(R.id.fragment_container, navigation)
                    beginTransaction?.commit()
                }
                is Categories -> {
                    val navigation: CategoriesFragment =
                        ARouter.getInstance().build(MyRouteTable.categoriesModule_CategoriesFragment).navigation(
                            activity,
                            LoginNavigationCallbackImpl()) as CategoriesFragment
                    beginTransaction?.replace(R.id.fragment_container, navigation)
                    beginTransaction?.commit()
                }
            }
        }
    }
}