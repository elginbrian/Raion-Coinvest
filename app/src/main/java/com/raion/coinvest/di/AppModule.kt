package com.raion.coinvest.di

import com.raion.coinvest.R
import com.raion.coinvest.model.remote.api.CoinMarketCapApi
import com.raion.coinvest.model.remote.auth.EmailAuthRepository
import com.raion.coinvest.model.remote.auth.TwitterAuthRepository
import com.raion.coinvest.model.remote.firestore.UserCollections
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideUsersCollections(): UserCollections {
        return UserCollections()
    }

    @Provides
    @Singleton
    fun provideCoinMarketCapApi(): CoinMarketCapApi{
        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinMarketCapApi::class.java)
    }
}