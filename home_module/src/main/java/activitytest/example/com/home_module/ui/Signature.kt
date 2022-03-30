package activitytest.example.com.home_module.ui

import activitytest.example.com.home_module.home.viewmodel.BlogViewModel
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Signature() {
    val viewModel: BlogViewModel = viewModel()
    val signatureSate = viewModel.signature.observeAsState()
    Log.d("-------------",signatureSate.value.toString()+"lllllll")
    Column(Modifier.verticalScroll(rememberScrollState())) {
        SignatureScreen(signature = signatureSate.value.toString(),
            contentColor = Color.Black,
            background = Color(0xFFF8F8FF))

    }
}

@Composable
fun SignatureScreen(signature:String, contentColor: Color, background:Color){
    Card(modifier = Modifier.fillMaxWidth(),contentColor = contentColor,elevation = 0.dp,backgroundColor = background) {
        Text(text = signature,fontSize = 25.sp,modifier = Modifier.padding(all = 20.dp),fontWeight = FontWeight.Bold,textAlign = TextAlign.Center)
    }
}