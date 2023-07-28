package com.vimal.kinzooandroidchallenge.domain.use_cases

import androidx.paging.PagingData
import com.vimal.kinzooandroidchallenge.data.repository.Repository
import com.vimal.kinzooandroidchallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getAllCharacters()
    }
}