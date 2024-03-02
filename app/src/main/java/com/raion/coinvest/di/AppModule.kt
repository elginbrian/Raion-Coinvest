package com.raion.coinvest.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.raion.coinvest.data.local.videoplayer.MetadataReader
import com.raion.coinvest.data.local.videoplayer.MetadataReaderImpl
import com.raion.coinvest.data.remote.api.CoinMarketCapApi
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.TwitterAuthRepository
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.ArticleCollections
import com.raion.coinvest.data.remote.firestore.UserCollections
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
    fun provideArticleCollections(): ArticleCollections{
        return ArticleCollections()
    }

    @Provides
    @Singleton
    fun provideImageRepository(): ImageRepository {
        return ImageRepository()
    }

    @Singleton
    @Provides
    fun provideVideoRepository(): VideoRepository {
        return VideoRepository()
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

    @Singleton
    @Provides
    fun provideVideoPlayer(app: Application): Player {
        return ExoPlayer.Builder(app).build()
    }

    @Singleton
    @Provides
    fun provideMetaDataReaderImpl(app: Application): MetadataReader {
        return MetadataReaderImpl(app)
    }
}