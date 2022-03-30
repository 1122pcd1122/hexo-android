package activitytest.example.com.log_module.project.ui.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(color: Color,theSize:Dp){
    Canvas(modifier = Modifier.size(size = theSize)){

        val canvasSize = size.maxDimension

        drawCircle(
            color = Color.Blue,
            radius = canvasSize / 2,
        )
    }
}

@Composable
fun LineCircle(lineColor: Color, modifier: Modifier, lineWidth: Float, circleColor: Color){


    Column(modifier = modifier,horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
        Canvas(modifier = Modifier.fillMaxWidth().weight(4f,true)){
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                color = lineColor,
                start = Offset(canvasWidth,0f),
                end = Offset(canvasWidth,canvasHeight),
                strokeWidth = lineWidth
            )

        }
        Canvas(modifier = Modifier.fillMaxWidth().weight(2f,true)){
            val radius = size.minDimension

            drawCircle(
                color = circleColor,
                radius = lineWidth / 2,
                center = this.center.plus(Offset(x = size.width / 2,y = 0f))
            )

        }
        Canvas(modifier = Modifier.fillMaxWidth().weight(4f,true)){
            val canvasHeight = size.height
            val canvasWidth = size.width

            drawLine(
                color = lineColor,
                start = Offset(canvasWidth,0f),
                end = Offset(canvasWidth,canvasHeight),
                strokeWidth = lineWidth
            )

        }
    }


}

@Composable
fun Line1(){

    Canvas(modifier = Modifier.size(50.dp)){
        val canvasHeight = size.height
        val canvasWidth = size.height

        drawLine(
            color = Color.Blue,
            start = Offset(canvasWidth / 2,0f),
            end = Offset(canvasWidth / 2,canvasHeight),
            strokeWidth = 10f
        )

    }

}

@Preview()
@Composable
fun Pre1(){
//    Circle(color = Color.Gray,theSize = 30.dp)


    LineCircle(lineColor = Color.White,lineWidth = 2f,circleColor = Color.Black,modifier = Modifier.width(10.dp).height(100.dp))

//    Line1()
}

