package activitytest.example.com.componentbase


import activitytest.example.com.componentbase.service.ContentModuleService

object ContentServiceFactory {

    private var contentModuleService:ContentModuleService? = null

    fun setFragmentService(contentModuleService : ContentModuleService?) {
        this.contentModuleService = contentModuleService
    }

    fun getFragmentService(): ContentModuleService? {
        return contentModuleService
    }

}