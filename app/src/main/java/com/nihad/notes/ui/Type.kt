package com.nihad.notes.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.nihad.notes.R

// Set of Material typography styles to start with

 val Moon = fontFamily(
        font(R.font.moon, FontWeight.Light),
        font(R.font.moon, FontWeight.Normal),
        font(R.font.moon, FontWeight.Medium),
        font(R.font.moon, FontWeight.SemiBold),
        font(R.font.moon, FontWeight.ExtraBold)
)

val typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */


)