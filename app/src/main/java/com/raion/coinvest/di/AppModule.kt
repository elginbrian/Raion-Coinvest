package com.raion.coinvest.di

import com.raion.coinvest.model.remote.auth.EmailAuthRepository
import com.raion.coinvest.model.remote.auth.TwitterAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEmailAuthRepository(): EmailAuthRepository {
        return EmailAuthRepository()
    }

    @Provides
    @Singleton
    fun provideTwitterAuthRepository(): TwitterAuthRepository {
        return TwitterAuthRepository()
    }
}