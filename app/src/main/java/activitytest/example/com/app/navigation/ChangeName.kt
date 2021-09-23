package activitytest.example.com.app.navigation

interface ChangeName {
    /**
     * 更改对应模块的导航名称
     */
    fun changeName(string: String?)

    /**
     * 获取对应模块的导航名
     */
    fun getNavName():String
}