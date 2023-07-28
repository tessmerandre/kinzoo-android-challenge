package com.vimal.kinzooandroidchallenge.data.repository

import androidx.paging.PagingData
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.CharacterDetail
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.domain.repository.RemoteDataSource
import com.vimal.kinzooandroidchallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    fun getAllCharacters(): Flow<PagingData<Character>> {
        return remoteDataSource.getAllCharacters()
    }

    fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>> {
        return remoteDataSource.getCharacter(characterId = characterId)
    }

    suspend fun getEpisode(episodeId: Int): Episode {
        return remoteDataSource.getEpisode(episodeId = episodeId)
    }
}