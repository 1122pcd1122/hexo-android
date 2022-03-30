package activitytest.example.com.base.arouterInterceptor

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.util.TokenUtil
import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

@Interceptor(priority = 1,name = "login")
class LoginInterceptor:IInterceptor {
    override fun init(context: Context?) {

    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val path = postcard?.path
        if (TokenUtil.isEmptyByToken() == true){
            //如果没有登录
           when(path){
               MyRouteTable.loginModule_MainActivity -> callback?.onContinue(postcard)
               else -> callback?.onInterrupt(null)
           }
        }else{
            callback?.onContinue(postcard)
        }


    }
}