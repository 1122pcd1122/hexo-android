package activitytest.example.com.login_module.screen.common


import activitytest.example.com.login_module.R
import activitytest.example.com.login_module.navigation.NavigationScreen
import activitytest.example.com.login_module.ui.theme.Black
import activitytest.example.com.login_module.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun ScreenTopBar(title:String){
    TopAppBar(backgroundColor = Color.Transparent,elevation = 0.dp) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Left,
                style = Typography.body1
            )
        }
    }

    Spacer(modifier = Modifier
        .height(25.dp)
        .fillMaxWidth())

}

@Composable
fun ScreenTitle(oneTitle:String = "",twoTitle:String = ""){
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = oneTitle,
                style = Typography.body2,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 15.dp)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = twoTitle,
                style = Typography.subtitle1,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }

    Spacer(modifier = Modifier
        .height(30.dp)
        .fillMaxWidth())
}

@Composable
fun UserNameTextField(userNameValue: MutableState<String>) {
    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userNameValue.value,
            onValueChange = {
                userNameValue.value = it
            },
            label = {
                Text(
                    text = "UserName/E-mail",
                    style = Typography.subtitle1,
                    textAlign = TextAlign.Left
                )
            },
            textStyle = Typography.caption,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,textColor = Black),
            trailingIcon = {
                if (userNameValue.value.isNotEmpty()){
                    IconButton(
                        onClick = {
                            userNameValue.value = ""
                        }
                    ) {
                        Icon(Icons.Filled.Clear,contentDescription = "clear",modifier = Modifier.size(25.dp))
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Composable
fun PassWordTextField(passwordValue: MutableState<String>) {

    val isShowPassWord = remember {
        mutableStateOf(true)
    }


    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = passwordValue.value,
            onValueChange = {
                passwordValue.value = it
            },
            textStyle = Typography.caption,
            label = {
                Text(text = "PassWord",style = Typography.subtitle1,textAlign = TextAlign.Left)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            trailingIcon = {
                if (passwordValue.value.isNotEmpty()){
                        IconButton(onClick = {
                             //点击
                            isShowPassWord.value = !isShowPassWord.value
                        },modifier = Modifier.size(25.dp)) {
                            Icon(Icons.Filled.Lock,contentDescription = "clear",modifier = Modifier.size(25.dp))
                        }
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = if (isShowPassWord.value){
                PasswordVisualTransformation()
            }else{
                VisualTransformation.None
            },
            maxLines = 1
        )
    }
}

@Composable
fun ScreenBottom(navController: NavHostController){
    val mutableListOf = mutableListOf<Int>()
    mutableListOf.add(R.drawable.qq)
    mutableListOf.add(R.drawable.wx)
    mutableListOf.add(R.drawable.github)


    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "其它登录方式",style = Typography.subtitle1)
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())
        Row {
            mutableListOf.forEach {
                ListImage(image = it)
            }
        }
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())
        Row {

            TextButton(onClick = { navController.navigate(NavigationScreen.Register().title){
            } }) {
                Text(text = "Don't have an account?",style = Typography.subtitle1)
                Text(text = " Sing Up",
                    style = Typography.subtitle1.plus(TextStyle(color = Black)),
                )
            }
        }
    }
}

@Composable
fun ListImage(image:Int){
    Spacer(modifier = Modifier.size(10.dp))
    Image(
        painter = painterResource(id = image),
        contentDescription = "logo",
        modifier = Modifier
            .clip(CircleShape.copy(CornerSize(100.dp)))
            .size(size = 30.dp)
    )

    Spacer(modifier = Modifier.size(10.dp))
}
