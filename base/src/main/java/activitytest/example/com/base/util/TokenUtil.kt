package activitytest.example.com.base.util



import activitytest.example.com.base.BaseApp
import activitytest.example.com.base.ModuleNames
import activitytest.example.com.base.MyRouteTable
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.launcher.ARouter
import java.io.*


@SuppressLint("StaticFieldLeak")
object TokenUtil {


    private val context: Context? = BaseApp.context

    private const val tokenFile:String = "token.txt"

    /**
     * 保存token
     * @param token token字符串
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun saveToken(token:String){

        try {
            val openFileOutput =
                context?.openFileOutput(tokenFile, Context.MODE_PRIVATE)
            openFileOutput?.write(token.encodeToByteArray())
            openFileOutput?.close()
            Log.d(ModuleNames.base_module,"token添加成功")
        }catch (e:IOException){
            Log.d(ModuleNames.base_module,"token添加失败")
            Log.d(ModuleNames.base_module,e.message.toString())
        }finally {
            Log.d(ModuleNames.base_module, "token字符串:$token")
        }

    }

    /**
     * 读取token
     */
    fun readToken(): String? {
        var tokenString:String? = null
        try {

            val openFileInput = context?.openFileInput(tokenFile)
            tokenString = openFileInput?.readBytes()?.decodeToString()
            openFileInput?.close()
            Log.d(ModuleNames.base_module, "token字符串:$tokenString")
            Log.d(ModuleNames.base_module,"读取成功")
            return tokenString
        }catch (e:IOException){
            Log.d(ModuleNames.base_module,"token添加失败")
            Log.d(ModuleNames.base_module,e.message.toString())
        }

        return tokenString

    }

    /**
     * 判断token是否为空
     *
     * @return true token为空 false token不为空
     */
    fun isEmptyByToken(): Boolean? {
        return readToken()?.isEmpty()
    }

    /**
     * 删除token
     */
    fun deleteToken(){
        try {
            val openFileOutput = context?.openFileOutput(tokenFile, Context.MODE_PRIVATE)
            openFileOutput?.write("".encodeToByteArray())
            openFileOutput?.close()
            Log.d(ModuleNames.base_module,"删除后的文件内容${readToken().toString()}")
            Log.d(ModuleNames.base_module,"token删除成功")
        }catch (e:IOException){
            Log.d(ModuleNames.base_module,"token删除失败")
        }
    }

    /**
     * 如果token为空表示token已经过期 直接跳转到登录接面
     */
    fun navToLogin() {
        ARouter.getInstance().build(MyRouteTable.loginModule_MainActivity).navigation()
    }

    fun navToApp(){
        ARouter.getInstance().build(MyRouteTable.homeModule_HomeFragment).navigation()
    }
}