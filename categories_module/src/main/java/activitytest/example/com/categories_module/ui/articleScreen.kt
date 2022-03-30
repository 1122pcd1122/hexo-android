package activitytest.example.com.categories_module.ui

import activitytest.example.com.base.MyRouteTable
import activitytest.example.com.categories_module.CategoriesViewModel
import activitytest.example.com.categories_module.bean.Article
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.launcher.ARouter

@ExperimentalMaterialApi
@Composable
fun ArticleScreen(categoriesViewModel: CategoriesViewModel) {

    val currentCategories = categoriesViewModel.currentCategories.observeAsState()
    if (currentCategories.value != null){


        val articleList =
            categoriesViewModel.articleByCategories(categories = currentCategories.value!!)
                .observeAsState()

        if (articleList.value?.isNotEmpty() == true){
            ArticleItemScreen(articleList)
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun ArticleItemScreen(articleList: State<List<Article>?>) {
    LazyColumn {
        items(articleList.value?.size!!){
            ContentItem(title = articleList.value!![it].title) {
                ARouter.getInstance().build(MyRouteTable.webActivityModule_MainActivity)
                    .withString("url", articleList.value!![it].href).navigation()
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