package activitytest.example.com.componentbase

import activitytest.example.com.componentbase.service.LabelModuleService


object LabelModuleFactory {

    private var homeModuleService: LabelModuleService? = null

    fun setFragmentService(labelModuleService:LabelModuleService?) {
        this.homeModuleService = labelModuleService
    }

    fun getFragmentService(): LabelModuleService? {
        return homeModuleService
    }

}