package activitytest.example.com.network_module

sealed class ApiResponse<T> {
    companion object{
        fun <T> create(error:Throwable):ApiResponse<T>{
            return ApiErrorResponse(error)
        }

        fun <T> create(body:T?):ApiResponse<T>{
            return if (body == null){
                ApiEmptyResponse()
            }else{
                ApiSuccessResponse(body)
            }
        }
    }
}

internal class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>()
