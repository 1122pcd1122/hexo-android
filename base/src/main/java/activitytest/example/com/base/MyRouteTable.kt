package activitytest.example.com.base

class MyRouteTable {
    companion object {
        const val homeModule_MainActivity: String = "/home_module/homepage"
        const val logModule_MainActivity: String = "/log_module/log"
        const val manageModule_MainActivity: String = "/manage_module/manage"
        const val labelModule_MainActivity: String = "/label_module/label"
        const val webActivityModule_MainActivity: String = "/content_module/content"
        const val loginModule_MainActivity:String = "/login_module/welcome"
        val moduleApps =
            arrayOf(homeModule_MainActivity, logModule_MainActivity, manageModule_MainActivity, labelModule_MainActivity, webActivityModule_MainActivity)


        const val homeModule_HomeFragment:String = "/home_module/homefragment"
        const val logModule_LogFragment:String = "/log_module/logfragment"
        const val manageModule_PersonFragment:String = "/manage_module/personfragment"
        const val labelModule_LabelFragment:String = "/label_module/labelfragment"

    }
}