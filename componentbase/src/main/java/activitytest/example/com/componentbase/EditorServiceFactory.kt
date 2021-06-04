package activitytest.example.com.componentbase

import activitytest.example.com.componentbase.service.EditorModuleService


object EditorServiceFactory {

    private var homeModuleService: EditorModuleService? = null
    fun setFragmentService(editorModuleService: EditorModuleService?) {
        this.homeModuleService = editorModuleService
    }

    fun getFragmentService(): EditorModuleService? {
        return homeModuleService
    }


}