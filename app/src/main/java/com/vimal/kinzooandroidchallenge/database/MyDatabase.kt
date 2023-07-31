package com.vimal.kinzooandroidchallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vimal.kinzooandroidchallenge.database.dao.character.CharacterDao
import com.vimal.kinzooandroidchallenge.domain.model.Character
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import com.vimal.kinzooandroidchallenge.domain.model.remote_key.CharacterRemoteKeys
import com.vimal.kinzooandroidchallenge.domain.model.remote_key.EpisodeRemoteKeys


@Database(
    entities = [
        Character::class, CharacterRemoteKeys::class,
        Episode::class, EpisodeRemoteKeys::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}