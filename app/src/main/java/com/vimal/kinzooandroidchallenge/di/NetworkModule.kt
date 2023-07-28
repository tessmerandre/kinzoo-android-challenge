package com.vimal.kinzooandroidchallenge.di

import androidx.paging.ExperimentalPagingApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vimal.kinzooandroidchallenge.data.API
import com.vimal.kinzooandroidchallenge.data.repository.RemoteDataSourceImpl
import com.vimal.kinzooandroidchallenge.database.MyDatabase
import com.vimal.kinzooandroidchallenge.domain.repository.RemoteDataSource
import com.vimal.kinzooandroidchallenge.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): API? {
        return try {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(API::class.java)
        }catch (e: Exception){
            null
        }
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        API: API?,
        myDatabase: MyDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            API = API,
            database = myDatabase
        )
    }

}