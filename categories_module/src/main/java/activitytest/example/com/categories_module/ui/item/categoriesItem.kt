package activitytest.example.com.categories_module.ui.item

import activitytest.example.com.categories_module.CategoriesViewModel
import activitytest.example.com.categories_module.nav.NavigationScreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun CategoriesButton(
    text: String,
    size: Dp,
    shape: Shape = RoundedCornerShape(CornerSize(50.dp)),
    contentColor: Color,
    backgroundColor: Color,
    onClick: (text: String) -> Unit,
) {
    OutlinedButton(
        onClick = { onClick(text) },
        modifier = Modifier.padding(horizontal = 3.dp,vertical = 3.dp),
        shape = shape,
        border = BorderStroke(1.dp, contentColor),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = backgroundColor,
            contentColor = contentColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
        )
    }

}


@ExperimentalMaterialApi
@Composable
fun CategoriesArea(navController: NavHostController, categoriesViewModel: CategoriesViewModel) {

    val categoriesState = categoriesViewModel.categories().observeAsState()

    if (categoriesState.value?.isNotEmpty() == true) {
        CategoriesButtons(categoriesState.value) {
            categoriesViewModel.updateCurrentCategories(it)
            navController.navigate(NavigationScreen.ArticleScreen().route)
        }
    }


}


@Composable
private fun CategoriesButtons(
    categoriesState: List<String>?,
    onClick: (title: String) -> Unit,
) {

    Column() {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)) {
            Text(text = "目前共有${categoriesState?.size}个标签",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 15.sp)
        }
        FlowRow(mainAxisAlignment = FlowMainAxisAlignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 16.dp)
                .padding(horizontal = 4.dp))
        {
            categoriesState?.forEach { it ->
                CategoriesButton(text = it,
                    size = 80.dp,
                    contentColor = Color.Black,
                    backgroundColor = Color.White) {
                    onClick(it)
                }
            }

        }
    }

}

//@Composable
//private fun CategoriesButtons(
//    line: Int?,
//    categoriesState: List<String>?,
//    last: Int?,
//    navController: NavHostController,
//    onClick: (title: String) -> Unit,
//) {
//    var index = 0
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top,
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//        Row(horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 5.dp)) {
//            Text(text = "目前共有${categoriesState?.size}个标签",
//                textAlign = TextAlign.Center,
//                color = Color.Black,
//                fontSize = 15.sp)
//        }
//
//        for (i in 1..line!!) {
//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically) {
//                for (j in 1..4) {
//                    CategoriesButton(text = categoriesState!![index++],
//                        size = if (categoriesState[index - 1].length > 5) 100.dp else 80.dp,
//                        contentColor = Color.Black,
//                        backgroundColor = Color.White) {
//                        onClick(it)
//                    }
//                }
//            }
//        }
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .padding(5.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically) {
//            for (y in 1..last!!) {
//                CategoriesButton(text = categoriesState!![index++],
//                    size = if (categoriesState[index - 1].length > 5) 120.dp else 60.dp,
//                    contentColor = Color.Black,
//                    backgroundColor = Color.White) {
//                    onClick(it)
//                }
//            }
//        }
//
//    }
//}

@Composable
fun Line(color: Color, modifier: Modifier) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(color = color,
            start = Offset(x = 0f, y = canvasHeight / 2),
            end = Offset(x = canvasWidth, y = canvasHeight / 2))
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true, showSystemUi = false)
fun Pre() {

}
