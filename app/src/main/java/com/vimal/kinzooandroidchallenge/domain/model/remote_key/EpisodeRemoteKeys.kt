package com.vimal.kinzooandroidchallenge.domain.model.remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vimal.kinzooandroidchallenge.util.Constant.EPISODE_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = EPISODE_REMOTE_KEYS_DATABASE_TABLE)
data class EpisodeRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)