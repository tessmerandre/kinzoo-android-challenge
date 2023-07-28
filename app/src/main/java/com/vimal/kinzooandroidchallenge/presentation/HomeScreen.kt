package com.vimal.kinzooandroidchallenge.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.vimal.kinzooandroidchallenge.R
import com.vimal.kinzooandroidchallenge.presentation.comman.ScreenWithErrorMessage
import com.vimal.kinzooandroidchallenge.presentation.screen.character.CharacterScreen
import com.vimal.kinzooandroidchallenge.presentation.screen.character.CharacterViewModel
import com.vimal.kinzooandroidchallenge.presentation.screen.character_detail.CharacterDetailScreen
import com.vimal.kinzooandroidchallenge.util.Constant
import com.vimal.kinzooandroidchallenge.util.Constant.CHARACTER_DETAIL_ARGUMENT_KEY

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi

@Composable
fun HomeScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass
) {
    val characters =
        characterViewModel.getAllHeroes.collectAsLazyPagingItems()

    if (characters.itemSnapshotList.items.isEmpty() ||
        characters.itemSnapshotList.items.any { it.id == -1 }
    )
        ScreenWithErrorMessage(modifier = Modifier.padding(16.dp), R.string.error_message)
    else {
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
}