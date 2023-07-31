package com.vimal.kinzooandroidchallenge.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Color.Companion.BasicBlack get() = Color(0xFF081F32)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Indigo = Color(0xFF5856D6)
val Gray1 = Color(0xFF8E8E93)
val Gray6 = Color(0xFFF2F2F7)
val Gray5 = Color(0xFFE5E5EA)

val Colors.bottomNavSelectedColor
    @Composable
    get() = Indigo

val Colors.bottomNavUnSelectedColor
    @Composable
    get() = Gray1

val Colors.shimmerEffectColor
    @Composable
    get() = Gray5

val Colors.detailHeaderBackgroundColor
    @Composable
    get() = Gray6

val Colors.episodeItemNameColor
    @Composable
    get() = Gray1

fun Color.Companion.statusTextColor(
    statusName: String
): Color {
    return when (statusName) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> {
            Color.Gray
        }
    }
}