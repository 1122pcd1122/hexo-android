package activitytest.example.com.manage_module.screen

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.base.util.TokenUtil
import activitytest.example.com.manage_module.bean.UpdateConfig
import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.manage_module.viewModel.PersonViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.launcher.ARouter


@Composable
fun SetScreen() {

    val personViewModel: PersonViewModel = viewModel()
    val userInfoState = personViewModel.userInfo().observeAsState()

    val blogName = remember {
        mutableStateOf(userInfoState.value?.blogName)
    }

//    val name = remember {
//        mutableStateOf(userInfoState.value?.name)
//    }

    val location = remember {
        mutableStateOf(userInfoState.value?.location)
    }

    val signature = remember {
        mutableStateOf(userInfoState.value?.signature)
    }

    val email = remember {
        mutableStateOf(userInfoState.value?.email)
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        UpdateBlogName(blogName = blogName){
            return@UpdateBlogName personViewModel.changeBlogName(blogName = UpdateConfig(blogName.value ?: ""))
        }
//        UpdateName(name = name)
        UpdateLocation(location = location){
            return@UpdateLocation personViewModel.changeLocation(location = UpdateConfig(location.value ?: ""))
        }
        UpdateSignature(signature = signature){
            return@UpdateSignature personViewModel.changeSignature(signature = UpdateConfig(signature.value ?: ""))
        }
        UpdateEmail(email = email){
            return@UpdateEmail personViewModel.changeEmail(email = UpdateConfig(email.value ?: ""))
        }



        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))

        OutlinedButton(
            onClick = {
                TokenUtil.deleteToken()
                ARouter.getInstance().build(MyRouteTable.loginModule_MainActivity).navigation()
            },
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.size(250.dp, 50.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black,
                backgroundColor = Color.White)
        ) {
            Text(text = "退出登录")
        }

    }

}

@Composable
fun UpdateBlogName(blogName: MutableState<String?>, updateBlogNameEvent : () -> LiveData<String>) {

    val isUpdateBlogName = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(value = blogName.value ?: "",
            onValueChange = {
                blogName.value = it
            },
            label = {
                Text(text = "博客名", color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black),
            trailingIcon = {

                IconButton(onClick = {
                    isUpdateBlogName.value = true
                    updateBlogNameEvent.invoke()
                }) {
                    Icon(imageVector = if (isUpdateBlogName.value) Icons.Default.Done else Icons.Default.DragHandle, contentDescription = "")
                }
            })
    }
}

//@Composable
//fun UpdateName(name: MutableState<String?>){
//    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
//        OutlinedTextField(value = name.value ?: "", onValueChange = {
//            name.value = it
//        },label = {
//            Text(text = "名称",color = Color.Black)
//        },colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black,cursorColor = Color.Black,focusedBorderColor = Color.Black,unfocusedBorderColor = Color.Black))
//    }
//}

@Composable
fun UpdateSignature(signature: MutableState<String?> , updateSignature: () -> LiveData<String>) {

    val isUpdateSignature = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(value = signature.value ?: "",
            onValueChange = {
                signature.value = it
            },
            label = {
                Text(text = "个性签名", color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black),
        trailingIcon = {
            IconButton(onClick = {
                isUpdateSignature.value = true
                updateSignature.invoke()
            }) {
                Icon(imageVector = if (isUpdateSignature.value) Icons.Default.Done else Icons.Default.DragHandle, contentDescription = "")
            }
        })
    }
}

@Composable
fun UpdateLocation(location: MutableState<String?>, updateLocationEvent : () -> LiveData<String>) {

    val isUpdateLocation = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(value = location.value ?: "",
            onValueChange = {
                location.value = it
            },
            label = {
                Text(text = "位置", color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black),
        trailingIcon = {
            IconButton(onClick = {
                isUpdateLocation.value = true
                updateLocationEvent.invoke()
            }) {
                Icon(imageVector = if (isUpdateLocation.value) Icons.Default.Done else Icons.Default.DragHandle, contentDescription = "")
            }
        })
    }
}

@Composable
fun UpdateEmail(email: MutableState<String?>, updateEmailEvent : () -> LiveData<String>) {

    val isUpdateEmail = remember {
        mutableStateOf(false)
    }


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(value = email.value ?: "",
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text = "邮箱", color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black),
        trailingIcon = {
            IconButton(onClick = {
                isUpdateEmail.value = true
                updateEmailEvent.invoke()
            }) {
                Icon(imageVector = if (isUpdateEmail.value) Icons.Default.Done else Icons.Default.DragHandle, contentDescription = "")
            }
        })
    }
}

