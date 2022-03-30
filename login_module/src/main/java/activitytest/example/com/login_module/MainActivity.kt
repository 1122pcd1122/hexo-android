package activitytest.example.com.login_module


import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.login_module.screen.LoginScreen
import activitytest.example.com.login_module.navigation.NavigationScreen
import activitytest.example.com.login_module.screen.RegisterScreen
import activitytest.example.com.login_module.screen.WelComeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import activitytest.example.com.login_module.ui.theme.WelcomeTheme

import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/login_module/welcome")
class MainActivity : ComponentActivity() {



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(MyRouteTable.loginModule_MainActivity,"创建Activity")
        setContent {
            WelcomeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavigationScreen.WelCome().title){
                    composable(NavigationScreen.WelCome().title) {
                       WelComeScreen(navController)
                    }
                    composable(NavigationScreen.Login().title) {
                        LoginScreen(this@MainActivity,navController)
                    }
                    composable(NavigationScreen.Register().title){
                        RegisterScreen(this@MainActivity,navController)
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}


