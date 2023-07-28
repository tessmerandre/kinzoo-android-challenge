package com.vimal.kinzooandroidchallenge.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.vimal.kinzooandroidchallenge.presentation.screen.character.CharacterScreen
import com.vimal.kinzooandroidchallenge.presentation.screen.character_detail.CharacterDetailScreen
import com.vimal.kinzooandroidchallenge.util.Constant
import com.vimal.kinzooandroidchallenge.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi

@Composable
fun HomeScreen(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass
) {

    NavHost(navController = navController, startDestination = "character_screen") {
        composable("character_screen") {
            CharacterScreen(
                widthSizeClass = widthSizeClass,
                onClickCharacterItem = { characterId ->
                    navController.navigate(
                        "character_detail_screen/$characterId"
                    )
                }
            )
        }

        composable(
            "character_detail_screen/{$CHARACTER_DETAIL_ARGUMENT_KEY}",
            arguments = listOf(
                navArgument(name = Constant.CHARACTER_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            CharacterDetailScreen(
                widthSizeClass = widthSizeClass,
            )
        }
    }
}






