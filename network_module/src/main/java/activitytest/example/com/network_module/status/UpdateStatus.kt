package activitytest.example.com.network_module.status

import activitytest.example.com.base.ModuleNames
import android.util.Log

class UpdateStatus {

    companion object{

        //网络状态
        private var status: NetStatus? = null

        //网络状态集合
        private val netStatusListeners:MutableList<(NetStatus) -> Unit> = mutableListOf()


        //状态更新
        fun update(status: NetStatus){
            Companion.status = status

            when (status) {
                StartStatus -> {
                    getNetStatusListener().forEach {
                        Log.d(ModuleNames.network_module,"请求开始")
                        it.invoke(StartStatus)
                    }
                }
                SuccessStatus -> {
                    getNetStatusListener().forEach {
                        Log.d(ModuleNames.network_module,"请求成功")
                        it.invoke(SuccessStatus)
                    }
                }
                ErrorStatus -> {
                    getNetStatusListener().forEach {
                        Log.d(ModuleNames.network_module,"请求失败")
                        it.invoke(ErrorStatus)
                    }
                }
                COMPLETEStatus -> {
                    getNetStatusListener().forEach {
                        Log.d(ModuleNames.network_module,"请求完成")
                        it.invoke(COMPLETEStatus)
                    }
                }
            }
        }

        fun addNetStatusListener(statusListener:(NetStatus) -> Unit){
            netStatusListeners.add(statusListener)
        }

        private fun getNetStatusListener():List<(NetStatus) -> Unit>{
            return netStatusListeners
        }

    }
}