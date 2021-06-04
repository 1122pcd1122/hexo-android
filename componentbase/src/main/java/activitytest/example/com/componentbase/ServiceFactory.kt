package activitytest.example.com.componentbase


import activitytest.example.com.componentbase.service.CommonService


interface ServiceFactory {

    fun setFragmentService(commonService: CommonService?)

    fun getFragmentService():CommonService?

}