package activitytest.example.com.base.arouterInterceptor

import activitytest.example.com.base.MyRouteTable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

class LoginNavigationCallbackImpl:NavigationCallback {
    override fun onFound(postcard: Postcard?) {

    }

    override fun onLost(postcard: Postcard?) {
    }

    override fun onArrival(postcard: Postcard?) {

    }

    override fun onInterrupt(postcard: Postcard?) {
       ARouter.getInstance().build(MyRouteTable.loginModule_MainActivity).navigation()
    }
}