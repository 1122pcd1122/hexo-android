package activitytest.example.com.componentbase

import activitytest.example.com.componentbase.service.CommonService
import activitytest.example.com.componentbase.service.HomeModuleService

object HomeServiceFactory {

    private var homeModuleService:HomeModuleService? = null
     fun setFragmentService(homeModuleService: HomeModuleService?) {
        this.homeModuleService = homeModuleService
    }

     fun getFragmentService(): HomeModuleService? {
        return homeModuleService
    }




}