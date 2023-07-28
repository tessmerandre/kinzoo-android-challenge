package com.vimal.kinzooandroidchallenge.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vimal.kinzooandroidchallenge.data.API
import com.vimal.kinzooandroidchallenge.data.remote.dto.toCharacter
import com.vimal.kinzooandroidchallenge.database.MyDatabase
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.remote_key.CharacterRemoteKeys
import com.vimal.kinzooandroidchallenge.util.Constant.CHARACTER_STARTING_PAGE_INDEX
import javax.inject.Inject

@ExperimentalPagingApi
class CharactersRemoteMediator @Inject constructor(
    private val api: API,
    private val database: MyDatabase
) : RemoteMediator<Int, Character>() {

    private val characterDao = database.characterDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: CHARACTER_STARTING_PAGE_INDEX
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = api.getAllCharacters(page = page)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        characterDao.deleteAllRemoteKeys()
                    }

                    val keys = response.results.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt(),
                        )
                    }

                    characterDao.addAllRemoteKeys(characterRemoteKeys = keys)
                    characterDao.addCharacters(response.results.toCharacter())
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterDao.getRemoteKeys(characterId = id)
            }
        }
    }
}

