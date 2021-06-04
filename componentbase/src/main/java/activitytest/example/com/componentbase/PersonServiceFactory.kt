package activitytest.example.com.componentbase


import activitytest.example.com.componentbase.service.PersonModuleService


object PersonServiceFactory {

    private var personModuleService:PersonModuleService? = null

    fun setFragmentService(personModuleService: PersonModuleService) {
        this.personModuleService = personModuleService
    }

    fun getFragmentService(): PersonModuleService? {
        return personModuleService
    }
}