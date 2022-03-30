package activitytest.example.com.manage_module.navigation

sealed class NavigationScreen(val title:String) {
    class MANAGE:NavigationScreen(title = "manage")
    class SET:NavigationScreen(title = "set")

}