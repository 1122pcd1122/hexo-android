package activitytest.example.com.login_module.screen

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.service.ActivityServiceFactory
import activitytest.example.com.login_module.bean.RegisterInform
import activitytest.example.com.login_module.screen.common.PassWordTextField
import activitytest.example.com.login_module.screen.common.ScreenTitle
import activitytest.example.com.login_module.screen.common.ScreenTopBar
import activitytest.example.com.login_module.screen.common.UserNameTextField
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class RegisterState(var message:String) {
    //注册开始
    Start(message = ""),

    //注册成功
    SUCCESS(message = ""),

    //注册失败
    ERROR(message = ""),

}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RegisterScreen(activity: Activity, navController: NavHostController) {
    Column {
        ScreenTopBar(title = "Blog")
        ScreenTitle(oneTitle = "Welcome Sign Up!", twoTitle = "Sign up to here")
        RegisterTextField(activity = activity)

        RegisterBottom()
    }
}


@Composable
fun ConfirmPassWordTextField(confirmPassWordValue: MutableState<String>) {
    val isShowPassWord = remember {
        mutableStateOf(true)
    }

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = confirmPassWordValue.value,
            onValueChange = {
                confirmPassWordValue.value = it
            },
            textStyle = Typography.caption,
            label = {
                Text(text = "Confirm PassWord",
                    style = Typography.subtitle1,
                    textAlign = TextAlign.Left)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            trailingIcon = {
                if (confirmPassWordValue.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            //点击
                            isShowPassWord.value = !isShowPassWord.value
                        }
                    ) {
                        Icon(Icons.Filled.Lock,
                            contentDescription = "clear",
                            modifier = Modifier.size(25.dp))
                    }

                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = if (isShowPassWord.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
    }
}

@Composable
fun RepositoryNameTextField(repositoryNameValue: MutableState<String>) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = repositoryNameValue.value,
            onValueChange = {
                repositoryNameValue.value = it
            },
            textStyle = Typography.caption,
            label = {
                Text(text = "Git repository name",
                    style = Typography.subtitle1,
                    textAlign = TextAlign.Left)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            trailingIcon = {
                if (repositoryNameValue.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            repositoryNameValue.value = ""
                        }
                    ) {
                        Icon(Icons.Filled.Clear,
                            contentDescription = "clear",
                            modifier = Modifier.size(25.dp))
                    }
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }

    Spacer(modifier = Modifier
        .height(30.dp)
        .fillMaxWidth())
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RegisterTextField(activity: Activity) {

    val userNameValue = remember {
        mutableStateOf("")
    }
    val passWordValue = remember {
        mutableStateOf("")
    }
    val confirmPassWordValue = remember {
        mutableStateOf("")
    }
    val repositoryNameValue = remember {
        mutableStateOf("")
    }


    RegisterEdittextScreen(userNameValue, passWordValue, confirmPassWordValue, repositoryNameValue)

    val viewModel: LoginViewModel = viewModel()
    val registerState = viewModel.registerState.observeAsState()

    RegisterButton(activity = activity, registerState = registerState) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.register(registerInform = RegisterInform(userNameValue.value,
                passWordValue.value,
                repositoryNameValue.value),confirmPassWord = confirmPassWordValue.value)
        }

    }
}

@Composable
private fun RegisterEdittextScreen(
    userNameValue: MutableState<String>,
    passWordValue: MutableState<String>,
    confirmPassWordValue: MutableState<String>,
    repositoryNameValue: MutableState<String>,
) {
    Column {

        UserNameTextField(userNameValue)
        PassWordTextField(passWordValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        ConfirmPassWordTextField(confirmPassWordValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        RepositoryNameTextField(repositoryNameValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
    }
}

@Composable
private fun RegisterBottom() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = "Forgot Password?", style = Typography.subtitle1, textAlign = TextAlign.Center)
    }
}

@Composable
fun RegisterButton(activity: Activity, registerState: State<RegisterState?>, onclick: () -> Unit) {

    //按钮状态
    val buttonState = remember {
        mutableStateOf(ButtonState.TEXT)
    }
    //网络状态变化下的按钮字体变化
    val statusText = remember {
        mutableStateOf("注册")
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = { onclick() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
            modifier = Modifier
                .background(Color.Transparent)
                .size(250.dp, 60.dp)
                .clip(CircleShape.copy(all = CornerSize(50.dp)))
        )
        {
            when (registerState.value) {
                RegisterState.Start -> {
                    //开始
                    buttonState.value = ButtonState.PROGRESS_INDICATOR
                }
                RegisterState.SUCCESS -> {
                    //错误
                    buttonState.value = ButtonState.TEXT
                    statusText.value = "注册"

                    ActivityServiceFactory.getService(MyRouteTable.app_MainActivity)?.startActivity(
                        activity = activity, null, "")
                }
                RegisterState.ERROR -> {
                    //错误
                    buttonState.value = ButtonState.TEXT
                    statusText.value = registerState.value!!.message
                }
                else -> ""
            }

            ButtonEvent(buttonState = buttonState, statusText = statusText)
        }
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


//
//@Preview(showSystemUi = true,showBackground = true)
//@Composable
//fun PreviewRegister(){
//}