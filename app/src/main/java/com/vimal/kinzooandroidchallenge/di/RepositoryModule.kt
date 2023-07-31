package com.vimal.kinzooandroidchallenge.di

import android.content.Context
import com.vimal.kinzooandroidchallenge.data.repository.DataStoreOperationsImpl
import com.vimal.kinzooandroidchallenge.data.repository.Repository
import com.vimal.kinzooandroidchallenge.domain.repository.DataStoreOperations
import com.vimal.kinzooandroidchallenge.domain.use_cases.GetAllCharactersUseCase
import com.vimal.kinzooandroidchallenge.domain.use_cases.GetCharacterDetailUseCase
import com.vimal.kinzooandroidchallenge.domain.use_cases.GetEpisodeUseCase
import com.vimal.kinzooandroidchallenge.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCase(
        repository: Repository
    ): UseCases {
        return UseCases(
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getCharacterDetailUseCase = GetCharacterDetailUseCase(repository),
            getEpisodeUseCase = GetEpisodeUseCase(repository)
        )
    }
}