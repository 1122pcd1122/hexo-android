package activitytest.example.com.log_module.project.ui.draw


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



@Composable
fun DottedLine(color: Color,intervals:FloatArray,modifier: Modifier = Modifier,strokeWidth:Float){

    val mutableListOf = mutableListOf<Offset>()
    for (i in 1 .. 15){
        mutableListOf.add(Offset(2f,2f))
    }

    Canvas(modifier = modifier){

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = color,
            start = Offset(x = 0f,y = canvasHeight / 2),
            end = Offset(canvasWidth,canvasHeight / 2),
            strokeWidth = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(
                intervals = intervals,20f)
        )
    }
}

@Preview
@Composable
fun Pre11(){
    DottedLine(color = Color.Blue,floatArrayOf(10f,10f),strokeWidth = 10f)
}
