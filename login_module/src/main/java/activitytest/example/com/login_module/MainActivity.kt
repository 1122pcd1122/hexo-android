package activitytest.example.com.login_module

import activitytest.example.com.login_module.screen.LoginScreen
import activitytest.example.com.login_module.navigation.NavigationScreen
import activitytest.example.com.login_module.screen.RegisterScreen
import activitytest.example.com.login_module.screen.WelComeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import activitytest.example.com.login_module.ui.theme.Pcds_BlogTheme
import activitytest.example.com.login_module.viewModel.LoginViewModel
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/login_module/welcome")
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Pcds_BlogTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavigationScreen.WelCome().title){
                    composable(NavigationScreen.WelCome().title) {
                       WelComeScreen(navController = navController)
                    }
                    composable(NavigationScreen.Login().title) {
                        LoginScreen(navController = navController)
                    }
                    composable(NavigationScreen.Register().title){
                        RegisterScreen()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pcds_BlogTheme {

    }
}