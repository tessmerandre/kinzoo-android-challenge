package com.vimal.kinzooandroidchallenge.presentation.comman

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vimal.kinzooandroidchallenge.R
import com.vimal.kinzooandroidchallenge.ui.theme.LOTTIE_ANIMATION_SIZE
import com.vimal.kinzooandroidchallenge.ui.theme.SMALL_PADDING

@Composable
fun ScreenWithErrorMessage(modifier: Modifier = Modifier,
                           @StringRes errorMessage: Int) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SMALL_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_lottie))

        LottieAnimation(
            modifier = Modifier.height(LOTTIE_ANIMATION_SIZE),
            composition = composition
        )


        Button(
            onClick = { },
            enabled = false
        ) {
            Text(
                text = stringResource(errorMessage).toUpperCase(locale = Locale.current),
            )
        }
    }
}