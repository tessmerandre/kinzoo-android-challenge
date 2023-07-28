package com.vimal.kinzooandroidchallenge.domain.use_cases

import com.vimal.kinzooandroidchallenge.data.repository.Repository
import com.vimal.kinzooandroidchallenge.domain.model.CharacterDetail
import com.vimal.kinzooandroidchallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(characterId: Int): Flow<Resource<CharacterDetail>> {
        return repository.getCharacter(characterId = characterId)
    }
}