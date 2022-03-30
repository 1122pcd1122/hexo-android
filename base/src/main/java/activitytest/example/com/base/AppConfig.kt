package activitytest.example.com.base

class AppConfig {
    companion object{
        private const val homeModule:String = "activitytest.example.com.home_module.HomeApp"
        private const val logModule:String = "activitytest.example.com.log_module.LogApp"
        private const val manageModule:String = "activitytest.example.com.manage_module.ManageApp"
        private const val labelModule:String = "activitytest.example.com.label_module.FriendApp"
        private const val webActivityModule:String = "activitytest.example.com.content_module.ContentApp"
        private const val categoriesModule:String = "activitytest.example.com.categories_module.CategoriesApp"
        val moduleApps = arrayOf(homeModule, logModule, manageModule, labelModule, webActivityModule,
            categoriesModule)

    }



}