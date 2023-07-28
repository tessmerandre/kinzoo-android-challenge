package com.vimal.kinzooandroidchallenge.domain.repository

import androidx.paging.PagingData
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.CharacterDetail
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.util.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllCharacters(): Flow<PagingData<Character>>

    fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>>

    suspend fun getEpisode(episodeId: Int): Episode
    suspend fun emitError(value: String)
}