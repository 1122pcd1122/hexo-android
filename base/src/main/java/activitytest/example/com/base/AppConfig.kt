package activitytest.example.com.base

class AppConfig {
    companion object{
        private const val homeModule:String = "activitytest.example.com.home_module.HomeApp"
        private const val logModule:String = "activitytest.example.com.log_module.LogApp"
        private const val personModule:String = "activitytest.example.com.person_module.PersonApp"
        private const val friendModule:String = "activitytest.example.com.friend_module.FriendApp"
        private const val editorModule:String = "activitytest.example.com.editor_module.EditorApp"
        private const val webActivityModule:String = "activitytest.example.com.content_module.ContentApp"
        val moduleApps = arrayOf(homeModule, logModule, personModule, friendModule, editorModule, webActivityModule)

    }


}