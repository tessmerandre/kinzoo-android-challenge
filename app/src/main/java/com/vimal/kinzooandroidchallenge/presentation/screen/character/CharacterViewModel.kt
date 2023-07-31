package com.vimal.kinzooandroidchallenge.presentation.screen.character

import androidx.lifecycle.ViewModel
import com.vimal.kinzooandroidchallenge.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    val getAllHeroes = useCases.getAllCharactersUseCase()
}