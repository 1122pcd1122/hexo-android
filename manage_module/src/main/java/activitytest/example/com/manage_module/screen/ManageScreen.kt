package activitytest.example.com.manage_module.screen


import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.manage_module.bean.UserData
import activitytest.example.com.manage_module.navigation.NavigationScreen
import activitytest.example.com.manage_module.screen.ui.theme.Typography
import activitytest.example.com.manage_module.viewModel.PersonViewModel
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter


/**
 * 管理页
 */
@ExperimentalCoilApi
@Composable
fun ManageScreen(activity: Activity, navHostController: NavHostController) {
    Column {
        //个人信息模块
        PersonInformation(navHostController)
        //中部导航栏
        MiddleBar()
        //仓库名称
        RepositoryName()
    }
}

@Composable
fun RepositoryName() {
    val viewModel: PersonViewModel = viewModel()
    val userInfoState = viewModel.userInfo().observeAsState(UserData())
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        Text(text = "仓库地址:",
            style = Typography.body2,
            modifier = Modifier
                .padding(start = 25.dp, end = 5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left)

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 10.dp)) {
            Text(text = "git@pcd11.top:/home/git/blog/${userInfoState.value?.repository}/${userInfoState.value?.repository}.git",
                style = Typography.subtitle1,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }
}


@Composable
fun MiddleBar() {

    val viewModel: PersonViewModel = viewModel()
    val articleNum = viewModel.articleNum().observeAsState()
    val tagNum = viewModel.tagNum().observeAsState()


    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(20.dp))

    Row(horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "文章", style = Typography.body2, textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
            Text(text = articleNum.value.toString(),
                style = Typography.subtitle1,
                textAlign = TextAlign.Justify)
        }

        Column(modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "日志", style = Typography.body2, textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))
                Text(text = articleNum.value.toString(),
                    style = Typography.subtitle1,
                    textAlign = TextAlign.Justify)
        }

        Column(modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "标签", style = Typography.body2, textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
            Text(text = tagNum.value.toString(),
                style = Typography.subtitle1,
                textAlign = TextAlign.Justify)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PersonInformation(navHostController: NavHostController) {

    val personViewModel: PersonViewModel = viewModel()
    val userInfo = personViewModel.userInfo().observeAsState(initial = UserData())



    val image = rememberImagePainter(
        data = userInfo.value?.userIcon.toString(),
        builder = {
            this.crossfade(true)
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .padding(start = 10.dp, top = 10.dp)
    ) {

        Surface(
            modifier = Modifier
                .weight(1f, false)
                .clip(RoundedCornerShape(100.dp))
                .size(80.dp),
            elevation = 0.dp
        ) {
            LeftArea(
                image,
                navHostController
            )
        }

        /**
         * 右边两列
         */
        Column(
            modifier = Modifier
                .weight(3f, true)
                .fillMaxHeight()
        ) {

            //第一行
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
            ) {

                //用户名
                Text(
                    text = userInfo.value?.name.toString(),
                    style = Typography.body1,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 10.dp)
                        .fillMaxWidth()

                )





            }


            //地址
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
            ) {
                Text(text = userInfo.value?.location.toString(), style = Typography.subtitle1, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp)
                )
            }


            //个性签名
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
            ) {
                Text(text = userInfo.value?.signature.toString(), style = Typography.subtitle1, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp)
                )
            }
        }
    }


}

/**
 * 头像部分
 */
@Composable
private fun LeftArea(image: ImagePainter, navHostController: NavHostController) {
    IconButton(onClick = {
        navHostController.navigate(NavigationScreen.SET().title)
    }) {
        Image(painter = image,
            contentDescription = "头像",
            modifier = Modifier
        )
    }
}

//@ExperimentalCoilApi
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewScreen() {
////    ManageScreen()
//
//    RepositoryName()
//}
