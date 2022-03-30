package activitytest.example.com.home_module.ui


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 通过按钮尺寸的变化来变化大小
 * @param num 按钮内容
 * @param contentColor 字体颜色
 * @param background 背景色
 * @param size 大小
 * @param shape 按钮形状
 * @param onClick 指示器点击事件
 */
@Composable
fun IndicatorSizeChange(
    num: String,
    contentColor: Color,
    background: Color,
    size: Dp,
    shape: Shape,
    fontSize:TextUnit = 5.sp,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        shape = shape,
        elevation = ButtonDefaults.elevation(0.dp,0.dp,0.dp),
        modifier = Modifier
            .size(size = size)
            .background(Color(0xFFF8F8FF)),
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        contentPadding = PaddingValues(1.dp)
    ) {
        Text(text = num,
            modifier = Modifier,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            color = contentColor)
    }
}

/**
 * 指示器底部的内容 默认样式
 *
 */
@Composable
fun DefaultIndicatorsBottom(width: Dp, color: Color) {
    Spacer(modifier = Modifier
        .width(width = width)
        .height(height = 10.dp)
        .background(color = color)
    )
}

/**
 * 指示器
 * @param size 指示器个数
 * @param initSize 初始大小
 * @param endSize 最终大小
 * @param shape 形状
 * @param contentColor 内容颜色
 * @param background 背景颜色
 * @param indicatorBottom 指示器底部内容
 */
@Composable
fun Indicators(
    size: Int,
    fontSize: TextUnit = 10.sp,
    selected: MutableState<Int?>,
    initSize: Dp = 20.dp,
    endSize: Dp = 25.dp,
    shape: Shape = RoundedCornerShape(CornerSize(100.dp)),
    contentColor: Color = Color.White,
    background: Color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
    onClink: (page: Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
    ) {
        for (i in 1..size) {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier
                .size(endSize)
                .animateContentSize()
            ) {
                //指示器按钮
                IndicatorSizeChange(
                    num = i.toString(),
                    contentColor = contentColor,
                    background = background,
                    shape = shape,
                    fontSize = fontSize,
                    size = if (selected.value == i) endSize else initSize)
                {
                    selected.value = i
                    onClink(i)
                }
            }
        }
    }


}


@Preview
@Composable
fun PreviewIndicatorButton() {
    IndicatorSizeChange(num = "1",
        contentColor = Color.White,
        background = MaterialTheme.colors.primary.copy(alpha = 0.4f),
        20.dp,
        shape = RoundedCornerShape(
            CornerSize(100.dp))) {

    }
}
