package com.ramesh.movieslist.di

import android.content.Context
import com.ramesh.movieslist.data.remote.NetworkService
import com.ramesh.movieslist.data.remote.Networking
import com.ramesh.movieslist.data.repository.MovieRepository
import com.ramesh.movieslist.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {


    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService =
        Networking.createAPI(
            "https://www.omdbapi.com/", context.cacheDir, 10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper =
        NetworkHelper(context)


    @Provides
    fun provideUserRepository(
        networkService: NetworkService
    ): MovieRepository {
        return MovieRepository(networkService)
    }

}