package com.nihad.notes.ui.splashscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.R
import com.nihad.notes.ui.Moon
import com.nihad.notes.ui.purple200
import com.nihad.notes.ui.white

@Preview(showBackground = true)
@Composable
fun SplashScreen() {

    Stack {
        Box(
            gravity = ContentGravity.Center,
            children = {
                // Here, SimpleText is a Composable function which is going to describe the contents of
                // this activity that will be rendered on the screen.

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Titles("NOTES APP")
                    Progressbar_App()
                }
            },
            modifier = Modifier

                .fillMaxSize()
                .offsetGradientBackground(listOf(white, white, purple200.copy(alpha = .20f)), 500.dp.value, 50f),
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
                    color = Color.Transparent
        ) {
            Image(
                    vectorResource(R.drawable.ic_notes),
                    alignment = Alignment.TopEnd,
                    alpha = 0.5f,
                    modifier = Modifier.preferredSize(90.dp, 90.dp)
                            .padding(end = 8.dp, top = 8.dp)

            )
        }



    }


}

@Composable
fun Progressbar_App() {
    Row(modifier = Modifier.padding(top = 32.dp).preferredSize(width = 110.dp,height = 20.dp) ,horizontalArrangement = Arrangement.SpaceBetween) {

        LinearProgressIndicator(color = purple200)
    }

}

@Composable
fun Titles(name: String) {
    Text(
            text = name,
            style = currentTextStyle().copy(
                    fontFamily = Moon,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 24.sp,
                    letterSpacing = 0.2.sp,

                    )
    )

}

fun Modifier.offsetGradientBackground(
        colors: List<Color>,
        width: Float,
        offset: Float = 0f
) = background(
        VerticalGradient(
                colors,
                startY = -offset,
                endY = width - offset,
                tileMode = TileMode.Clamp
        )
)

fun Modifier.gradientTint(
        colors: List<Color>,
        blendMode: BlendMode,
        brushProvider: (List<Color>, Size) -> LinearGradient
) = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        drawContent()
        size = this.size
        drawRect(
                brush = gradient,
                blendMode = blendMode
        )
    }
}

//fun Modifier.diagonalGradientTint(
//    colors: List<Color>,
//    blendMode: BlendMode
//) = gradientTint(colors, blendMode) { gradientColors, size ->
//    LinearGradient(
//        colors = gradientColors,
//        startX = 0f,
//        startY = 0f,
//        endX = size.width,
//        endY = size.height
//    )
//}

//fun Modifier.horizontalGradientBackground(
//    colors: List<Color>
//) = gradientBackground(colors) { gradientColors, size ->
//    VerticalGradient(
//        colors = gradientColors,
//        startY = 0f,
//        endY = size.height
//    )
//}
//fun Modifier.gradientBackground(
//    colors: List<Color>,
//    brushProvider: (List<Color>, Size) -> LinearGradient
//): Modifier = composed {
//    var size by remember { mutableStateOf(Size.Zero) }
//    val gradient = remember(colors, size) { brushProvider(colors, size) }
//    drawWithContent {
//        size = this.size
//        drawRect(brush = gradient)
//        drawContent()
//    }
//}
