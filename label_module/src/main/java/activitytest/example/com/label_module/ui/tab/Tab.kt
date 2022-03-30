package activitytest.example.com.label_module.ui.tab

import activitytest.example.com.label_module.viewModel.LabelsViewModel
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TabRows() {
    val labelsViewModel: LabelsViewModel = viewModel()

    val listLabels = labelsViewModel.listLabels().observeAsState()

    val currentIndex = remember {
        mutableStateOf(0)
    }

    Tabs(listLabels, currentIndex,init = {labelsViewModel.articles(listLabels.value!![0])}) {
        labelsViewModel.articles(it)
    }
}

@Composable
fun Tabs(
    listLabels: State<List<String>?>,
    currentIndex: MutableState<Int>,
    init: (() -> Unit)?,
    onClick: (title: String) -> Unit,
) {
    if (listLabels.value?.isNotEmpty() == true){

        if (init != null) {
            init()
        }

        ScrollableTabRow(
            selectedTabIndex = currentIndex.value,
            edgePadding = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = Color.Black,
        ) {
            listLabels.value!!.forEachIndexed { index, title ->
                Tab(selected = currentIndex.value == index, onClick = {
                    currentIndex.value = index
                    onClick(title)
                }, text = {
                    TabItem(title = title)
                })
            }
        }
    }
}

@Composable
fun TabItem(title: String) {
    Text(text = title, style = MaterialTheme.typography.button)
}