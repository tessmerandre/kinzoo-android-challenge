package com.vimal.kinzooandroidchallenge.di

import android.app.Application
import androidx.room.Room
import com.vimal.kinzooandroidchallenge.database.MyDatabase
import com.vimal.kinzooandroidchallenge.util.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(
        app: Application
    ): MyDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            MyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}