package com.example.homescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.homescreen.ui.theme.HomescreenTheme

@Composable
fun circularbar(navController: NavController) {
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .height(275.dp)
        ,
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Text("\n   KITCHEN SUMMARY",
            style = TextStyle(
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(" \n\n\n\n              Health Index\n" +
                    "                of Kitchen",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            var healthindex = (healthcar / sizelist)
            var floathealthindex = (healthindex) * 1f
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                circularprogressor(percentage = floathealthindex, number = sizelist)

                Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Click here to show more",
                    modifier = Modifier.clickable {
                        navController.navigate("proceedscreen")
                    },
                    style = TextStyle(
                        color = White,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic
                        )
                )

            }

        }
    }
}
@Composable
fun circularprogressor(
    percentage: Float,
    number: Int,
    fontSize: TextUnit= 48.sp,
    radius:Dp= 28.dp,
    color : Color = Color.Green ,
    strokeWidth:Dp=18.dp,
    animDuration: Int = 1000,
    animationDelay : Int =0
    // use animation delay if u have more circularProgressbar


){
    var healthindex=(healthcar / sizelist)*100
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage= animateFloatAsState(
        targetValue = if (animationPlayed ) percentage else 0f,
        animationSpec = tween(
            durationMillis =animDuration,
            delayMillis = animationDelay,
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier= Modifier.size(radius * 6f )
    ){
        Canvas(modifier = Modifier.size(radius*6f)){
            drawArc(
                color= color,
                //start degree of bar
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                //use center will dissappear contour lines
                style = Stroke(strokeWidth.toPx(),cap= StrokeCap.Round)
            )
        }

    }
    Text(text=(healthindex).toInt().toString()+"%",
        color= Color.White,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
    )
}
//(curPercentage.value* number).toInt().toString()
@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    HomescreenTheme {
       // circularbar()
    }
}