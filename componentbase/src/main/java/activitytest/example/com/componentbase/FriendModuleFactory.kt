package activitytest.example.com.componentbase

import activitytest.example.com.componentbase.service.FriendModuleService


object FriendModuleFactory {

    private var homeModuleService: FriendModuleService? = null
    fun setFragmentService(friendModuleService:FriendModuleService?) {
        this.homeModuleService = friendModuleService
    }

    fun getFragmentService(): FriendModuleService? {
        return homeModuleService
    }

}