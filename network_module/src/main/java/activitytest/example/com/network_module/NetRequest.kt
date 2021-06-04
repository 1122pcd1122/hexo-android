package activitytest.example.com.network_module

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class NetRequest {
    companion object{
        fun <ResultType> request(dsl: RequestAction<ResultType, ResultType>.() -> Unit):LiveData<ResultData<ResultType>?>{

            return  requestLiveData(Dispatchers.IO,dsl)

        }

        private fun <ResponseType,ResultType> requestLiveData(coroutineContext: CoroutineContext, dsl:RequestAction<ResponseType,ResultType>.() -> Unit): LiveData<ResultData<ResultType>?> {
            val action = RequestAction<ResponseType, ResultType>().apply(dsl)

            return liveData(coroutineContext){

                //网络请求开始
                emit(ResultData.start<ResultType>())

                //获取网络请求数据
                val apiResponse = try {
                    val resultBean = action.api?.invoke()
                    ApiResponse.create<ResponseType>(resultBean)
                }catch (e:Throwable){
                    ApiResponse.create<ResponseType>(e)
                }

                //根据ApiResponse类型,处理对应事物
                val result = when (apiResponse){
                    is ApiEmptyResponse ->{
                        null
                    }
                    is ApiSuccessResponse ->{
                        //转换数据
                        val result = action.transformer?.invoke(apiResponse.body)

                        //提交获取成功状态
                        result.apply {
                            emit(ResultData.seccess<ResultType>(this))
                        }
                    }
                    is ApiErrorResponse ->{
                        emit(ResultData.error<ResultType>(apiResponse.throwable))
                        null
                    }
                }

                //网络请求结束
                emit(ResultData.complete<ResultType>(result))
            }
        }




    }
}