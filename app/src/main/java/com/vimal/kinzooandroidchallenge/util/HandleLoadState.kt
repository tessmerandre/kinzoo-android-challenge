package com.vimal.kinzooandroidchallenge.util

import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.vimal.kinzooandroidchallenge.presentation.comman.CharacterShimmerEffectListType
import com.vimal.kinzooandroidchallenge.presentation.comman.ErrorScreen

@Composable
fun handleLoadState(
    loadState: CombinedLoadStates,
    onRetryClick: () -> Unit,
    isEmptyList: Boolean
): Boolean {

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }


    return when {
        loadState.refresh is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading -> {
            CharacterShimmerEffectListType()
            false
        }

        error != null && isEmptyList -> { ErrorScreen(onRetryClick = onRetryClick)
            false
        }

        else -> {
            true
        }
    }

}