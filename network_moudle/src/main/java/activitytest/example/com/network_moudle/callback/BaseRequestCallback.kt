package activitytest.example.com.network_moudle.callback

import activitytest.example.com.network_moudle.exception.BaseHttpException

class BaseRequestCallback(internal var onStart:(() -> Unit)?,
                          internal var onCancelled:(() -> Unit)?,
                          internal var onFiled:((BaseHttpException) -> Unit)?,
                          internal var onFilToast:(() -> Boolean) = {true},
                          internal var onFinally:(() -> Unit)?) {


    /*
        在现实Loading之后且开始网络请求之前执行
     */
    fun onStart(block:() -> Unit){
        this.onStart = block
    }


    /*
        如果外部主动取消了网络请求,不会回调onFail,而是回调此方法,随后回调onFinally
        但如果当取消了网络请求时已回调了 onSuccess / onSuccessIO 方法,则不会回调此方法
     */
    fun onCancelled(block: () -> Unit){
        this.onCancelled = block
    }

    /*
        当网络请求失败时会调用此方法,在onFinally被调用之前执行
     */
    fun onFailed(block: (BaseHttpException) -> Unit){
        this.onFiled = block
    }

    /*
        用于控制是否当网络请求失败时Toast失败原因
     */
    fun onFailToast(block: () -> Boolean){
        this.onFilToast = block
    }

    /*
        当网络请求结束之后(不管请求成功与否)且隐藏Loading之前执行
     */
    fun onFinally(block: () -> Unit){
        this.onFinally = block
    }






}