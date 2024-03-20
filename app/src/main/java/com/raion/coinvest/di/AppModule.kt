package com.raion.coinvest.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.raion.coinvest.data.local.exoPlayer.MetadataReader
import com.raion.coinvest.data.local.exoPlayer.MetadataReaderImpl
import com.raion.coinvest.data.remote.api.ApiRepository
import com.raion.coinvest.data.remote.api.CoinGeckoApi
import com.raion.coinvest.data.remote.api.GoApi
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.TwitterAuthRepository
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.PdfRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.PostCollections
import com.raion.coinvest.data.remote.firestore.CommentCollections
import com.raion.coinvest.data.remote.firestore.CourseCollections
import com.raion.coinvest.data.remote.firestore.LikeCollections
import com.raion.coinvest.data.remote.firestore.NewsCollections
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
    fun provideEmailAuthRepository(imageRepository: ImageRepository): EmailAuthRepository {
        return EmailAuthRepository(imageRepository)
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
    fun provideArticleCollections(imageRepository: ImageRepository): PostCollections{
        return PostCollections(imageRepository)
    }

    @Provides
    @Singleton
    fun provideCourseCollections(): CourseCollections {
        return CourseCollections()
    }

    @Provides
    @Singleton
    fun provideCommentCollections(): CommentCollections {
        return CommentCollections()
    }

    @Singleton
    @Provides
    fun provideLikeCollections(): LikeCollections {
        return LikeCollections()
    }

    @Provides
    @Singleton
    fun provideNewsCollections(): NewsCollections {
        return NewsCollections()
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

    @Singleton
    @Provides
    fun providePdfRepository(): PdfRepository {
        return PdfRepository()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): CoinGeckoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGoApi(): GoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.goapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(coinGeckoApi: CoinGeckoApi, goApi: GoApi): ApiRepository{
        return ApiRepository(coinGeckoApi, goApi)
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