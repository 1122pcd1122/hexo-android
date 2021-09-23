package activitytest.example.com.login_module.navigation

sealed class NavigationScreen(val title:String) {
    class WelCome: NavigationScreen(title = "WelComeScreen")
    class Login:NavigationScreen(title = "LoginScreen")
    class Register:NavigationScreen(title = "RegisterScreen")
}