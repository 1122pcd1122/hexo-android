package activitytest.example.com.app.activity


import activitytest.example.com.app.screen.ui.DefaultTopBar
import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.MainActivityBinding
import activitytest.example.com.base.arouterInterceptor.LoginNavigationCallbackImpl
import activitytest.example.com.app.screen.ui.MyBottomNavigation
import activitytest.example.com.app.screen.ui.Screen
import activitytest.example.com.log_module.project.ui.theme.AppTheme
import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.service.ActivityServiceFactory
import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.home_module.home.HomeFragment
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.databinding.DataBindingUtil
import coil.annotation.ExperimentalCoilApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


@Route(path = "/app/main")
class MainActivity : AppCompatActivity() {

    private var mainActivityBinding: MainActivityBinding? = null


    @ExperimentalAnimationApi
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)




        //NavFactory设置activity
        Screen.Screens.setActivity(this)

        //顶部导航
        mainActivityBinding?.topBar?.setContent {
            AppTheme {
                DefaultTopBar()
            }
        }

        //展示底部导航栏
        mainActivityBinding?.bottomBar?.setContent {
            AppTheme {
                val currentScreen  = remember {
                    mutableStateOf<Screen>(Screen.Home)
                }

                MyBottomNavigation(screens = Screen.Screens.listScreen, currentScreenId = currentScreen.value.id, onItemSelected = {
                    currentScreen.value = it
                    Screen.Screens.navToFragment(it)

                })
            }
        }


        //跳转到homeFragment
        navToHomeFragment()
    }


    override fun onStart() {
        super.onStart()

        //判断token是否存在
        if (TokenUtil.isEmptyByToken() == true){
            ActivityServiceFactory.getService(MyRouteTable.loginModule_MainActivity)?.startActivity(this,null,"登录")
        }


    }



    /**
     * fragmentLayout切换到HomeFragment
     */
    private fun navToHomeFragment() {
        val navigation: HomeFragment =
            ARouter.getInstance().build(MyRouteTable.homeModule_HomeFragment).navigation(this,
                LoginNavigationCallbackImpl()) as HomeFragment
        val supportFragmentManager = supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fragment_container, navigation)
        beginTransaction.commit()
    }


    override fun onStop() {
        super.onStop()

        if (TokenUtil.isEmptyByToken() == true){
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }


}