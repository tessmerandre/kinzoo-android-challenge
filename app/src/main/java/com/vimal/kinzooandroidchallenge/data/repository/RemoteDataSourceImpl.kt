package com.vimal.kinzooandroidchallenge.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vimal.kinzooandroidchallenge.data.API
import com.vimal.kinzooandroidchallenge.data.paging_source.CharactersRemoteMediator
import com.vimal.kinzooandroidchallenge.data.remote.dto.toCharacterDetail
import com.vimal.kinzooandroidchallenge.data.remote.dto.toEpisode
import com.vimal.kinzooandroidchallenge.database.MyDatabase
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.CharacterDetail
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.domain.repository.RemoteDataSource
import com.vimal.kinzooandroidchallenge.util.Constant.ITEMS_PER_PAGE
import com.vimal.kinzooandroidchallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalPagingApi
class RemoteDataSourceImpl @Inject constructor(
    private val API: API,
    private val database: MyDatabase
) : RemoteDataSource {

    private val characterDao = database.characterDao()

    override fun getAllCharacters(): Flow<PagingData<Character>> {

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharactersRemoteMediator(
                api = API,
                database = database
            ),
            pagingSourceFactory = { characterDao.getAllCharactersDB() }
        ).flow
    }

    override fun getCharacter(characterId: Int): Flow<Resource<CharacterDetail>> {
        return flow {
            emit(Resource.Loading())
            val response = API.getCharacter(id = characterId)
            val characterDetail = response.toCharacterDetail()
            emit(Resource.Success(data = characterDetail))

        }.catch {
            emit(Resource.Error(message = "Does not have a internet connection"))
        }
    }

    override suspend fun getEpisode(episodeId: Int): Episode {
        return API.getEpisode(id = episodeId).toEpisode()
    }
}