package activitytest.example.com.log_module.project.ui

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.log_module.project.bean.Article
import activitytest.example.com.log_module.project.ui.draw.DottedLine
import activitytest.example.com.log_module.project.ui.draw.LineCircle
import activitytest.example.com.log_module.project.util.changeNumToDigits
import activitytest.example.com.log_module.project.util.changeStringToNumber
import activitytest.example.com.log_module.project.viewModel.LogViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.launcher.ARouter


@Composable
fun LogItemCard(article: Article) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .height(80.dp).clickable {
            ARouter.getInstance().build(MyRouteTable.webActivityModule_MainActivity)
                .withString("url", article.href).navigation()
        },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LineCircle(modifier = Modifier
            .width(20.dp)
            .fillMaxHeight()
            .padding(all = 5.dp),
            circleColor = MaterialTheme.colors.primary,
            lineWidth = 10f,
            lineColor = MaterialTheme.colors.primary.copy(alpha = 0.1f)
        )

        val content = changeStringToNumber(article.month) + "-" + changeNumToDigits(article.day.substring(
            0,
            article.day.length - 1))

        Column(modifier = Modifier.padding(start = 10.dp,end = 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(all = 3.dp)) {
                Text(
                    text = content,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(10.dp))

                val title =  if (article.title.length > 20){
                    article.title.substring(0, 18)
                }else {
                    article.title
                }
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colors.onSecondary
                )
            }

            DottedLine(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),color = MaterialTheme.colors.primary.copy(alpha = 0.1f), intervals = floatArrayOf(15f,15f), strokeWidth = 4f)
        }

    }
}


@ExperimentalFoundationApi
@Composable
fun Logs(){
    val logViewModel:LogViewModel = viewModel()

    val observeAsState = logViewModel.log().observeAsState()

    val value = observeAsState.value
    LazyColumn {
        if (observeAsState.value?.isNotEmpty() == true){
            items(count = observeAsState.value?.size!!){ header ->
                val article = value?.get(header)
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Text(text = article?.year.toString())
                }

                Column {

                    article?.listArticle?.forEach {
                        LogItemCard(it)
                    }

                }
            }
        }
    }



}

@Composable
@Preview
fun Pre(){
    val article = Article(month = "Septmember",day = "1,",title = "java管道")
    LogItemCard(article)
}