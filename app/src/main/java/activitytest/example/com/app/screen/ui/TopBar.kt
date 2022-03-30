package activitytest.example.com.app.screen.ui

import activitytest.example.com.app.viewModel.APPViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun TopBar(
    navButton: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    backgroundColor: Color = Color(0xFFF8F8FF),
    contentColor: Color = Color(0xFF333333),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
){
    //三部分占比是: 1 : 2 : 1
    TopAppBar(backgroundColor = backgroundColor,contentColor = contentColor,elevation = elevation) {

        if (navButton != null){

            //左边部分
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                navButton()
            }
        }

        if (title != null){
            //中间部分
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                title()
            }
        }

        if (actions != null){
            //右边的不是
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End
            )
            {
                actions()
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun DefaultTopBar(){

    val viewModel:APPViewModel = viewModel()
    val observeAsState = viewModel.configuration().observeAsState(initial = null)

    TopBar(
        navButton = {
            Spacer(modifier = Modifier.size(10.dp))
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        },
        title = {
            Text(text = observeAsState.value?.blogName.toString(),color = Color.Black,fontSize = 25.sp,fontWeight = FontWeight.Bold)
        },
        actions = {
            IconButton(onClick = {
                Screen.Screens.navToFragment(Screen.Manage)
            }) {
                Image(
                    painter = rememberImagePainter(
                        data = observeAsState.value?.userIcon,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    )
}