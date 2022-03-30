package activitytest.example.com.login_module.screen


import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.service.ActivityServiceFactory
import activitytest.example.com.login_module.bean.User
import activitytest.example.com.login_module.screen.common.*
import activitytest.example.com.login_module.ui.theme.Typography
import activitytest.example.com.login_module.ui.theme.White
import activitytest.example.com.login_module.viewModel.LoginViewModel
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * 按钮状态
 */
enum class ButtonState {
    //文章
    TEXT,
    //指示器
    PROGRESS_INDICATOR
}

enum class LoginState {
    //登录开始
    Start,

    //登录成功
    SUCCESS,

    //登录失败
    ERROR,

    //登录完成
    DONE
}


@RequiresApi(Build.VERSION_CODES.R)
@Composable
        /**
         * 登录主页
         *
         */
fun LoginScreen(activity: Activity, navController: NavHostController) {



    Column {
        //顶部状态栏
        ScreenTopBar(title = "Blog")
        //标题
        ScreenTitle(oneTitle = "Welcome Back!", twoTitle = "Sign in to continue")
        //登录事件
        LoginTopEvent(activity = activity)

        //底部栏
        ScreenBottom(navController = navController)
    }

}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun LoginTopEvent(activity: Activity) {
    //用户名
    val userNameValue = remember {
        mutableStateOf("")
    }

    //密码
    val passWordValue = remember {
        mutableStateOf("")
    }


    //登录状态
    val loginViewModel:LoginViewModel = viewModel()
    val loginState = loginViewModel.loginState.observeAsState()

    //登录输入框
    LoginTextField(userNameValue, passWordValue)

    //登录按钮
    LoginButton(activity = activity,loginState){
        //开始登录
        CoroutineScope(Dispatchers.Main).launch {
            loginViewModel.login(user = User(username = userNameValue.value,
                password = passWordValue.value))
        }
    }

}


/**
 * 登录框
 * @param userNameValue 用户名
 * @param passWordValue 密码
 */
@Composable
fun LoginTextField(userNameValue: MutableState<String>, passWordValue: MutableState<String>) {

    Column {
        //用户名
        UserNameTextField(userNameValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        //密码
        PassWordTextField(passWordValue)
    }

    Spacer(modifier = Modifier
        .height(80.dp)
        .fillMaxWidth())
}


/**
 * 登录按钮
 * @param loginState  登录状态
 */
@Composable
private fun LoginButton(activity: Activity,loginState: State<LoginState?>, onClick:() -> Unit) {

    //网络状态变化下的按钮字体变化
    val statusText = remember {
        mutableStateOf("登录")
    }

    //按钮状态
    val buttonState = remember {
        mutableStateOf(ButtonState.TEXT)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
            onClick()


        },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
            modifier = Modifier
                .background(Color.Transparent)
                .size(250.dp, 60.dp)
                .clip(CircleShape.copy(all = CornerSize(50.dp)))
        )
        {

            when(loginState.value){
                LoginState.Start ->{
                    //开始
                    buttonState.value = ButtonState.PROGRESS_INDICATOR
                }
                LoginState.SUCCESS ->{
                    //错误
                    buttonState.value = ButtonState.TEXT
                    statusText.value = "登录"
                    ActivityServiceFactory.getService(MyRouteTable.app_MainActivity)?.startActivity(
                        activity = activity,null,"")
                }
                LoginState.ERROR ->{
                    //错误
                    buttonState.value = ButtonState.TEXT
                    statusText.value = "登录失败"
                }
                LoginState.DONE -> {
                    //完成
                    buttonState.value = ButtonState.TEXT
                    statusText.value = "登录"
                }
            }

            //按钮变化事件
            ButtonEvent(buttonState,statusText)

        }
    }

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(30.dp))

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Forgot Password?", style = Typography.subtitle1, textAlign = TextAlign.Center)
    }

    Spacer(modifier = Modifier
        .height(30.dp)
        .fillMaxWidth())

}


/**
 * 登录的按钮变化事件
 */
@Composable
private fun ButtonEvent(buttonState: MutableState<ButtonState>, statusText: MutableState<String>) {
    if (buttonState.value == ButtonState.TEXT) {
        Text(text = statusText.value, style = Typography.subtitle2, color = White)
    } else {
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 2.dp,
            modifier = Modifier.size(24.dp)
        )
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewContent() {
//    val rememberNavController = rememberNavController()
//    LoginScreen(rememberNavController)
//}