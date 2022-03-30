package activitytest.example.com.label_module.ui

import activitytest.example.com.label_module.ui.tab.Content
import activitytest.example.com.label_module.ui.tab.TabRows
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable


@ExperimentalMaterialApi
@Composable
fun LabelScreen(){
    Column {
        TabRows()
        Content()
    }
}