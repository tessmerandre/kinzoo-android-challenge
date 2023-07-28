package com.vimal.kinzooandroidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.vimal.kinzooandroidchallenge.presentation.HomeScreen
import com.vimal.kinzooandroidchallenge.ui.theme.KinzooAndroidChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
@ExperimentalUnitApi
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            val navHostController = rememberNavController()
            KinzooAndroidChallengeTheme {
                // A surface container using the 'background' color from the theme
                val widthSizeClasses =
                    calculateWindowSizeClass(this).widthSizeClass

                HomeScreen(
                    navController = navHostController,
                    widthSizeClass = widthSizeClasses
                )
            }
        }
    }
}