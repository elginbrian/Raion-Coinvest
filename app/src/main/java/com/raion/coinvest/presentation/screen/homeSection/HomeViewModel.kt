package com.raion.coinvest.presentation.screen.homeSection

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.api.ApiRepository
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.CourseCollections
import com.raion.coinvest.data.remote.firestore.NewsCollections
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val courseCollections: CourseCollections,
    private val videoRepository: VideoRepository,
    private val imageRepository: ImageRepository,
    private val newsRepository: NewsCollections,
): ViewModel() {
    private var isFetching = false
    private var isFetching2 = false
    fun getTrendingSearchList(
        onFinished: (GetTrendingSearchList) -> Unit
    ){
        if(!isFetching){
            isFetching = true
            viewModelScope.launch {
                val result = apiRepository.getTrendingSearchList()
                isFetching = false
                onFinished(result)
            }
        }
    }

    fun getCourse(
        onFinished: (MutableList<CourseDataClass>) -> Unit
    ) {
        if (!isFetching2) {
            isFetching2 = true

            viewModelScope.launch {
                val courseList = courseCollections.getCourse()
                val videoFetchDeferreds = mutableListOf<Deferred<Unit>>()

                for (course in courseList) {
                    for (video in course.courseContent) {
                        val videoFetchDeferred = async {
                            getVideo(courseId = course.courseId, videoId = video.videoId) {
                                video.videoUri = it
                            }
                        }
                        videoFetchDeferreds.add(videoFetchDeferred)
                    }
                }

                videoFetchDeferreds.awaitAll()
                isFetching2 = false
                onFinished(courseList)
            }
        }
    }

    private fun getVideo(
        courseId: String,
        videoId: String,
        onFinished: (Uri) -> Unit
    ){
        viewModelScope.launch {
            val videoUri = videoRepository.getVideo(courseId, videoId)
            onFinished(videoUri)
        }
    }

    private var isFetching3 = false
    fun getNews(
        onFinished: (MutableList<NewsDataClass>) -> Unit
    ){
        if (!isFetching3) {
            isFetching3 = true
            viewModelScope.launch {
                val newsList = newsRepository.getNews()
                val imageFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for (news in newsList) {
                    val imageFetchDeferred = CompletableDeferred<Unit>()
                    imageFetchDeferreds.add(imageFetchDeferred)

                    getImage(news.newsId) { imageUri ->
                        news.imageUri = imageUri
                        imageFetchDeferred.complete(Unit)
                    }
                }
                imageFetchDeferreds.awaitAll()

                isFetching3 = false
                onFinished(newsList)
            }
        }
    }

    private fun getImage(
        articleId: String,
        onFinished: (Uri) -> Unit
    ){
        viewModelScope.launch {
            val imageUri = imageRepository.getImage(articleId)
            onFinished(imageUri)
        }
    }
}