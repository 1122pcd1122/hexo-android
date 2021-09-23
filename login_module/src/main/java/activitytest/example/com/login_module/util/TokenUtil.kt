package activitytest.example.com.login_module.util


import activitytest.example.com.login_module.LoginApp
import android.content.Context



object TokenUtil {


    fun saveToken(token:String){
        val sharedPreferences =
            LoginApp().applicationContext.getSharedPreferences("token", Context.MODE_PRIVATE) ?: return

        with( sharedPreferences.edit() ){
            putString("token",token)
            apply()
        }

    }
}