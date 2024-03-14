package com.raion.coinvest.presentation.screen.debugging

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.api.ApiRepository
import com.raion.coinvest.data.remote.api.model.GetListWithMarketData
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.PostCollections
import com.raion.coinvest.data.remote.firestore.CourseCollections
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugViewModel2 @Inject constructor(
    private val postCollections: PostCollections,
    private val courseCollections: CourseCollections,
    private val imageRepository: ImageRepository,
    private val videoRepository: VideoRepository,
    private val apiRepository: ApiRepository
): ViewModel() {
    val _articleList = mutableStateOf<MutableList<PostDataClass>>(mutableListOf())

    fun addNewPost(post: PostDataClass) = viewModelScope.launch{
        postCollections.addPost(post)
        imageRepository.uploadPostImage(post)
    }
    fun addNewCourse(course: CourseDataClass) = viewModelScope.launch {
        courseCollections.addCourse(course)
        videoRepository.uploadVideo(course)
    }

    private var isFetching = false

    fun getListWithMarketData(
        onFinished: (List<GetListWithMarketData>) -> Unit
    ){
        if(!isFetching){
            isFetching = true
            viewModelScope.launch {
                val result = apiRepository.getListWithMarketData()
                isFetching = false
                onFinished(result)
            }
        }
    }

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

    fun getPost(
        onFinished: (MutableList<PostDataClass>) -> Unit
    ){
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                val articleList = postCollections.getPost()
                val imageFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for (article in articleList) {
                    val imageFetchDeferred = CompletableDeferred<Unit>()
                    imageFetchDeferreds.add(imageFetchDeferred)

                    getImage(article.postId) { imageUri ->
                        article.imageUri = imageUri
                        imageFetchDeferred.complete(Unit)
                    }
                }
                imageFetchDeferreds.awaitAll()

                isFetching = false
                onFinished(articleList)
            }
        }
    }

    fun getCourse(
        onFinished: (MutableList<CourseDataClass>) -> Unit
    ) {
        if (!isFetching) {
            isFetching = true

            viewModelScope.launch {
                try {
                    val courseList = courseCollections.getCourse()

                    val videoFetchDeferred = mutableListOf<CompletableDeferred<Unit>>()

                    for (course in courseList) {
                        val videoDeferred = CompletableDeferred<Unit>()
                        videoFetchDeferred.add(videoDeferred)

//                        getVideo(course.courseId) { videoUri ->
////                            course.courseContent.add(
////                                CourseContent(
////                                    videoTitle = course.courseName,
////                                    videoDescription = course.courseName,
////                                    videoUri = videoUri
////                                )
////                            )
//                            videoDeferred.complete(Unit)
//                        }
                    }

                    videoFetchDeferred.awaitAll()

                    isFetching = false
                    onFinished(courseList)
                } catch (e: Exception) {
                    isFetching = false
                    onFinished(mutableListOf()) // or handle error case appropriately
                }
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

//    private fun getVideo(
//        courseId: String,
//        onFinished: (Uri) -> Unit
//    ){
//        viewModelScope.launch {
//            val videoUri = videoRepository.getVideo(courseId)
//            onFinished(videoUri)
//        }
//    }
}