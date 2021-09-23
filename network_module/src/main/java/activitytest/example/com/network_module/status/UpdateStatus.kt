package activitytest.example.com.network_module.status

import activitytest.example.com.network_module.*

class UpdateStatus {

    companion object{

        private var status: NetStatus? = null
        private val netStatusListener:MutableList<(NetStatus) -> Unit> = mutableListOf()


        fun update(status: NetStatus){
            Companion.status = status

            when (status) {
                StartStatus -> {
                    getNetStatusListener().forEach {
                        it.invoke(StartStatus)
                    }
                }
                SuccessStatus -> {
                    getNetStatusListener().forEach {
                        it.invoke(SuccessStatus)
                    }
                }
                ErrorStatus -> {
                    getNetStatusListener().forEach {
                        it.invoke(ErrorStatus)
                    }
                }
                COMPLETEStatus -> {
                    getNetStatusListener().forEach {
                        it.invoke(COMPLETEStatus)
                    }
                }
            }
        }

        fun addNetStatusListener(statusListener:(NetStatus) -> Unit){
            netStatusListener.add(statusListener)
        }

        private fun getNetStatusListener():List<(NetStatus) -> Unit>{
            return netStatusListener
        }

    }
}