package com.vimal.kinzooandroidchallenge.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.vimal.kinzooandroidchallenge.util.isCompactScreen

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@ExperimentalUnitApi
@Composable
fun androidx.compose.material.Typography.captionSize(widthSizeClass: WindowWidthSizeClass): TextUnit {
    return if (widthSizeClass.isCompactScreen()) {
        TextUnit(value = MaterialTheme.typography.caption.fontSize.value, type = TextUnitType.Sp)
    } else {
        TextUnit(value = 16.0f, type = TextUnitType.Sp)
    }
}

@ExperimentalUnitApi
@Composable
fun androidx.compose.material.Typography.body1Size(widthSizeClass: WindowWidthSizeClass): TextUnit {
    return if (widthSizeClass.isCompactScreen()) {
        TextUnit(value = MaterialTheme.typography.body1.fontSize.value, type = TextUnitType.Sp)
    } else {
        TextUnit(value = 16.0f, type = TextUnitType.Sp)
    }
}