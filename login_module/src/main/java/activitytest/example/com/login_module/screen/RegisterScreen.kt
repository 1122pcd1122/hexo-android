package activitytest.example.com.login_module.screen

import activitytest.example.com.login_module.screen.common.PassWordTextField
import activitytest.example.com.login_module.screen.common.ScreenTitle
import activitytest.example.com.login_module.screen.common.ScreenTopBar
import activitytest.example.com.login_module.screen.common.UserNameTextField
import activitytest.example.com.login_module.ui.theme.Typography
import activitytest.example.com.login_module.ui.theme.White
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(){
    Column {
        ScreenTopBar(title = "Blog")

        Spacer(modifier = Modifier
            .height(15.dp)
            .fillMaxWidth())

        ScreenTitle(oneTitle = "Welcome Sign Up!",twoTitle = "Sign up to here")

        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())

        RegisterTextField()

        Spacer(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth())

        RegisterButton {

        }

        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())

        RegisterBottom()
    }
}


@Composable
fun ConfirmPassWordTextField(){

    val confirmPasswordValue = remember {

        mutableStateOf("")
    }

    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = confirmPasswordValue.value,
            onValueChange = {
                confirmPasswordValue.value = it
            },
            textStyle = Typography.caption,
            label = {
                Text(text = "Confirm PassWord",style = Typography.subtitle1,textAlign = TextAlign.Left)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            trailingIcon = {
                if (confirmPasswordValue.value.isNotEmpty()){
                    IconButton(
                        onClick = {
                            confirmPasswordValue.value = ""
                        }
                    ) {
                        Icon(Icons.Filled.Clear,contentDescription = "clear",modifier = Modifier.size(25.dp))
                    }
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}
@Composable
fun RepositoryNameTextField(){
    val repositoryName = remember {

        mutableStateOf("")
    }
    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = repositoryName.value,
            onValueChange = {
                repositoryName.value = it
            },
            textStyle = Typography.caption,
            label = {
                Text(text = "Git repository name",style = Typography.subtitle1,textAlign = TextAlign.Left)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            trailingIcon = {
                if (repositoryName.value.isNotEmpty()){
                    IconButton(
                        onClick = {
                            repositoryName.value = ""
                        }
                    ) {
                        Icon(Icons.Filled.Clear,contentDescription = "clear",modifier = Modifier.size(25.dp))
                    }
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }
}
@Composable
fun RegisterTextField(){
    Column {
        val userNameValue = remember {
            mutableStateOf("")
        }
        UserNameTextField(userNameValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        val passWordValue = remember{
            mutableStateOf("")
        }

        PassWordTextField(passWordValue)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        ConfirmPassWordTextField()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        RepositoryNameTextField()
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
fun RegisterButton(onclick:() -> Unit){
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
        Button(onClick = { onclick() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
            modifier = Modifier
                .background(Color.Transparent)
                .size(250.dp, 60.dp)
                .clip(CircleShape.copy(all = CornerSize(50.dp)))
        )
        {
            Text(text = "Sign up Now",style = Typography.subtitle2,color = White)
        }
    }

}


@Preview(showSystemUi = true,showBackground = true)
@Composable
fun PreviewRegister(){
}