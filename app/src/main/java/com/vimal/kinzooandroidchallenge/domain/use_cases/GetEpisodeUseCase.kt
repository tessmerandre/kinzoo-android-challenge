package com.vimal.kinzooandroidchallenge.domain.use_cases

import com.vimal.kinzooandroidchallenge.data.repository.Repository
import com.vimal.kinzooandroidchallenge.domain.model.Episode
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(episodeId: Int): Episode {
        return repository.getEpisode(episodeId = episodeId)
    }
}