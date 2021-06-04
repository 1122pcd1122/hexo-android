package activitytest.example.com.componentbase

import activitytest.example.com.componentbase.service.CommonService
import activitytest.example.com.componentbase.service.HomeModuleService
import activitytest.example.com.componentbase.service.LogModuleService

object LogServiceFactory {

    private var logModuleService:LogModuleService? = null

    fun setFragmentService(logModuleService: LogModuleService?) {
        this.logModuleService = logModuleService
    }

    fun getFragmentService(): LogModuleService? {
        return logModuleService
    }

}