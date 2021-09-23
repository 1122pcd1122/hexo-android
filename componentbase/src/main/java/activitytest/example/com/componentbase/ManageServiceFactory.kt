package activitytest.example.com.componentbase


import activitytest.example.com.componentbase.service.ManageModuleService


object ManageServiceFactory {

    private var personModuleService:ManageModuleService? = null

    fun setFragmentService(manageModuleService: ManageModuleService) {
        this.personModuleService = manageModuleService
    }

    fun getFragmentService(): ManageModuleService? {
        return personModuleService
    }
}