package activitytest.example.com.base.service

object ActivityServiceFactory {

    private val serviceMap:MutableMap<String,CommonService> = mutableMapOf()

    fun setService(path:String,commonService: CommonService){
        if (!serviceMap.containsKey(path)){
            serviceMap[path] = commonService
        }
    }

    fun getService(path: String): CommonService? {
        return serviceMap[path]
    }

}