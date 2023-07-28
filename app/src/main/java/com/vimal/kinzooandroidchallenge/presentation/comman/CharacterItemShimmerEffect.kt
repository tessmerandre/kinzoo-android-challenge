package com.vimal.kinzooandroidchallenge.presentation.comman

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vimal.kinzooandroidchallenge.ui.theme.MEDIUM_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.SMALL_PADDING
import com.vimal.kinzooandroidchallenge.ui.theme.Shapes
import com.vimal.kinzooandroidchallenge.ui.theme.shimmerEffectColor

@Composable
fun CharacterItemShimmerEffectContent(
    alpha: Float
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha)
            .height(180.dp)
            .border(1.dp, color = MaterialTheme.colors.shimmerEffectColor, Shapes.large),
        color = Color.White,
        shape = Shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .alpha(alpha),
                color = MaterialTheme.colors.shimmerEffectColor
            ) {
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SMALL_PADDING)
            ) {
                Surface(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .alpha(alpha),
                    color = MaterialTheme.colors.shimmerEffectColor
                ) {
                }
                Surface(
                    modifier = Modifier
                        .height(10.dp)
                        .padding(start = SMALL_PADDING)
                        .fillMaxWidth(0.6f)
                        .alpha(alpha),
                    color = MaterialTheme.colors.shimmerEffectColor,
                    shape = Shapes.medium
                ) {
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING)
                    .height(15.dp)
                    .alpha(alpha),
                color = MaterialTheme.colors.shimmerEffectColor,
                shape = Shapes.medium
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterShimmerItemPreview() {
    CharacterItemShimmerEffectContent(alpha = 0.4f)
}