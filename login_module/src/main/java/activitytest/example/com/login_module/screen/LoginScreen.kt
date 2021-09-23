package activitytest.example.com.login_module.screen


import activitytest.example.com.login_module.bean.User
import activitytest.example.com.login_module.screen.common.*
import activitytest.example.com.login_module.ui.theme.Typography
import activitytest.example.com.login_module.ui.theme.White
import activitytest.example.com.login_module.viewModel.LoginViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * 按钮状态
 */
enum class ButtonState {
    Normal, Pressed
}


@Composable
        /**
         * 登录主页
         * @param navController 导航控制器
         */
fun LoginScreen(navController: NavHostController) {

    //用户名
    val userNameValue = remember {
        mutableStateOf("")
    }

    //密码
    val passWordValue = remember {
        mutableStateOf("")
    }

    Column {
        //顶部状态栏
        ScreenTopBar(title = "Blog")
        //标题
        ScreenTitle(oneTitle = "Welcome Back!", twoTitle = "Sign in to continue")
        //登录输入框
        LoginTextField(userNameValue, passWordValue)
        //登录按钮
        LoginButton(user = User(name = userNameValue.value, password = passWordValue.value))
        //底部栏
        ScreenBottom(navController)
    }

}

/**
 * 登录点击事件
 * @param loginState 登录状态
 */

@Composable
fun LoginClickEvent(loginState: State<Any>) {
    if (loginState.value == true) {
        //登录成功
        LoginAlertDialog(title = "登录成功", content = "恭喜你登录成功")
    } else {
        //登录失败
        LoginAlertDialog(title = "登录失败", content = "登录失败")
    }
}

@Composable
fun LoginAlertDialog(title: String, content: String) {
    val isShowDialog = remember {
        mutableStateOf(true)
    }
    if (isShowDialog.value) {

        AlertDialog(onDismissRequest = {
            isShowDialog.value = false
        },
            buttons = {
                Row {
                    Button(
                        onClick = {
                            isShowDialog.value = false
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomStart = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "取消")
                    }
                    Button(
                        onClick = {
                            isShowDialog.value = false
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(bottomEnd = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "确定")
                    }
                }
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = content)
            },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            properties = DialogProperties()
        )
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
 * @param user 用户信息
 */
@Composable
private fun LoginButton(user: User) {

    val loginViewModel: LoginViewModel = viewModel()
    val observeAsState = loginViewModel.loginState.observeAsState("false")


    val buttonState = remember {
        mutableStateOf(ButtonState.Normal)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = {

            CoroutineScope(Dispatchers.Main).launch {
                loginViewModel.login(user = user)
            }
            //按钮状态变化
            if (buttonState.value == ButtonState.Normal) {
                buttonState.value = ButtonState.Pressed
            } else {
                buttonState.value = ButtonState.Normal
            }
        },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
            modifier = Modifier
                .background(Color.Transparent)
                .size(250.dp, 60.dp)
                .clip(CircleShape.copy(all = CornerSize(50.dp)))
        )
        {
            if (buttonState.value == ButtonState.Normal) {
                Text(text = "Login Now", style = Typography.subtitle2, color = White)
            } else {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    if (observeAsState.value == true) {
        LoginClickEvent(loginState = observeAsState)
    } else {
        LoginClickEvent(loginState = observeAsState)
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewContent() {
    val rememberNavController = rememberNavController()
    LoginScreen(rememberNavController)
}