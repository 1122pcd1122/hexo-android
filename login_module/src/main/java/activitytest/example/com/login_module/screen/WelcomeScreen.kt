package activitytest.example.com.login_module.screen


import activitytest.example.com.login_module.R
import activitytest.example.com.login_module.navigation.NavigationScreen
import activitytest.example.com.login_module.screen.common.ScreenBottom
import activitytest.example.com.login_module.screen.common.ScreenTopBar
import activitytest.example.com.login_module.ui.theme.Black
import activitytest.example.com.login_module.ui.theme.Typography
import activitytest.example.com.login_module.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController



@Composable
fun WelComeScreen(navController: NavHostController) {
    Column {
        ScreenTopBar(title = "Blog")
        WelcomeContent()
        WelComeButton(navController = navController)

        Spacer(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth())

        ScreenBottom(navController = navController)
    }
}

@Composable
fun WelcomeContent(){
    Column {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "backGround",

            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Hello!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = Typography.body2
                )
        }

        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Text(text = "欢迎使用我的博客插件来浏览并管理您的博客",
                maxLines = 2,
                modifier = Modifier.padding(start = 20.dp,end = 20.dp,top = 5.dp,bottom = 5.dp),
                style = Typography.subtitle1,
                textAlign = TextAlign.Center)
        }
    }
}



@Composable
fun WelComeButton(navController: NavHostController) {
    Spacer(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth())
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
        SignIn(navController = navController)
        Spacer(modifier = Modifier.width(50.dp))
        SignUp(navController = navController)
    }
}

@Composable
fun SignIn(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate(NavigationScreen.Login().title)
        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .background(Color.Transparent)
            .size(130.dp, 50.dp)
            .clip(CircleShape.copy(all = CornerSize(50.dp)))
    ) {
        Text(text = "Login",style = Typography.subtitle2,color = White)
    }
}

@Composable
fun SignUp(navController: NavHostController) {

    Button(onClick = {navController.navigate(NavigationScreen.Register().title)  },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier
            .background(Color.Transparent)
            .size(130.dp, 50.dp)
            .border(width = 1.dp, color = Black,
                CircleShape.copy(CornerSize(50.dp)))
            .clip(CircleShape.copy(CornerSize(50.dp)))
    ) {
        Text(text = "Sign Up",style = Typography.subtitle2,modifier = Modifier.background(Color.Transparent),color = Black)
    }
}



