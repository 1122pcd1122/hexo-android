package activitytest.example.com.home_module.ui

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.home_module.home.viewmodel.BlogViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.launcher.ARouter


@ExperimentalMaterialApi
@Composable
fun Articles() {
    val viewModel: BlogViewModel = viewModel()

    val pageNumState = viewModel.pageNum.observeAsState()

    val currentPage = viewModel.currentPage.observeAsState()

    val articleList = viewModel.articleByPage(currentPage.value!!).observeAsState()
    Column {


        //文章列表指示器
        Indicators(pageNumState.value ?: 1, selected = currentPage as MutableState<Int?>) {
            viewModel.updateCurrentPage(it)
        }

        //文章列表
        LazyColumn {
            items(
                items = articleList.value ?: emptyList(),
                itemContent = {
                    ArticleItem(title = it.title,
                        time = it.month + " " + it.day + " " + it.year,
                        background = White) {
                        ARouter.getInstance().build(MyRouteTable.webActivityModule_MainActivity)
                            .withString("url", it.href).navigation()
                    }
                }
            )
        }


    }
}


@ExperimentalMaterialApi
@Composable
fun ArticleItem(
    title: String,
    time: String,
    background: Color,
    onClink: () -> Unit,
) {
    Card(onClick = { onClink() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 8.dp, bottom = 8.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        backgroundColor = background
    ) {
        Column(modifier = Modifier
            .padding(all = 25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier
                .height(5.dp)
                .fillMaxWidth())
            Text(text = time, style = MaterialTheme.typography.subtitle2, color = Gray)
        }

    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewArticleItem() {
    ArticleItem(title = "Https初探", time = "July 2, 2021", background = White) {

    }
}
