package com.vimal.kinzooandroidchallenge.database.dao.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.remote_key.CharacterRemoteKeys

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAllCharactersDB(): PagingSource<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<Character>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM character_remote_keys_table WHERE id=:characterId")
    suspend fun getRemoteKeys(characterId: Int): CharacterRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(characterRemoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM character_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}