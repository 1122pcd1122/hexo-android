package activitytest.example.com.network_module

open class RequestAction<ResponseType,ResultType> {
    var api:(suspend () -> ResponseType)? = null

    @Suppress("UNCHECKED_CAST")
    var transformer: ((ResponseType?) -> ResultType?)? = {
        it as? ResultType
    }

    fun api(block:suspend () -> ResponseType){
        this.api = block
    }

    fun transformer(block: (ResponseType?) -> ResultType?){
        this.transformer = block
    }

}


