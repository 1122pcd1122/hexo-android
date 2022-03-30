package activitytest.example.com.label_module.ui.tab

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.viewModel.LabelsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.launcher.ARouter


@ExperimentalMaterialApi
@Composable
fun Content(){
    val labelsViewModel:LabelsViewModel = viewModel()

    val listLabels = labelsViewModel.articles.observeAsState()
    Contents(listLabels = listLabels)
}

@ExperimentalMaterialApi
@Composable
fun Contents(listLabels: State<List<Article>?>){
    LazyColumn {
        if (listLabels.value != null){
            items(count = listLabels.value!!.size){
                ContentItem(title = listLabels.value!![it].title) {
                    ARouter.getInstance().build(MyRouteTable.webActivityModule_MainActivity)
                        .withString("url", listLabels.value!![it].href).navigation()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ContentItem(title:String, onClick:()->Unit){
    Card(onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 8.dp, bottom = 8.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
    ) {
        Column(modifier = Modifier
            .padding(all = 25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, style = MaterialTheme.typography.h6)
        }

    }
}