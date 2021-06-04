package activitytest.example.com.network_module

class ResultData<T> (val requestStatus: RequestStatus,val data:T?,val error:Throwable? = null) {

    companion object{
        /**
         * 请求开始
         */
        fun <T> start():ResultData<T>{
            return ResultData(RequestStatus.START,null,null)
        }

        /**
         * 请求成功
         */
        fun <T> seccess(data:T?):ResultData<T>{
            return ResultData(RequestStatus.SUCCESS,data,null)
        }

        /**
         * 请求完成
         */
        fun <T> complete(data:T?):ResultData<T>{
            return ResultData(RequestStatus.COMPLETE,data,null)
        }

        /**
         * 请求失败
         */

        fun <T> error(error: Throwable?):ResultData<T>{
            return ResultData(RequestStatus.ERROR,null,error)
        }
    }
}