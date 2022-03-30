package activitytest.example.com.app.screen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun MyBottomNavigation(screens:List<Screen>, currentScreenId:String, onItemSelected:(Screen) -> Unit){
    Row(
        modifier = Modifier
            .background(Color(0xFFF8F8FF))
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { item ->
            MyBottomNavItem(item = item, isSelected = item.id == currentScreenId) {
                onItemSelected(item)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyBottomNavItem(item:Screen,isSelected:Boolean,onClick:() -> Unit){

    val background = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ){
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = item.icon, contentDescription = null,tint = contentColor)

            AnimatedVisibility(visible = isSelected) {
                Text(text = item.title, fontSize = 15.sp,color = contentColor,textAlign = TextAlign.Center)
            }
        }
    }
}