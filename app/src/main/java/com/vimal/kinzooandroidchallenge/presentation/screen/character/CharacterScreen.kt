package com.vimal.kinzooandroidchallenge.presentation.screen.character

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vimal.kinzooandroidchallenge.presentation.components.CharacterListContent

@ExperimentalCoilApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    onClickCharacterItem: (Int) -> Unit
) {
    val characters =
        characterViewModel.getAllHeroes.collectAsLazyPagingItems()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            swipeRefreshState.isRefreshing = true
            characters.refresh()
            swipeRefreshState.isRefreshing = false
        },
    ) {
        CharacterListContent(
            characters = characters,
            widthSizeClass = widthSizeClass,
            onClickCharacterItem = onClickCharacterItem
        )
    }
}
