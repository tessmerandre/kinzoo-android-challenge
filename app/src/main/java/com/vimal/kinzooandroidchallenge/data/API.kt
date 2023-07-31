package com.vimal.kinzooandroidchallenge.data

import com.vimal.kinzooandroidchallenge.data.remote.dto.CharacterDto
import com.vimal.kinzooandroidchallenge.data.remote.dto.EpisodeDto
import com.vimal.kinzooandroidchallenge.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int? = null
    ): Response<CharacterDto>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDto

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): EpisodeDto

}