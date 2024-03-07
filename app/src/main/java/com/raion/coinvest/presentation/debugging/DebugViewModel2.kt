package com.raion.coinvest.presentation.debugging

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.ArticleCollections
import com.raion.coinvest.data.remote.firestore.CourseCollections
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugViewModel2 @Inject constructor(
    private val articleCollections: ArticleCollections,
    private val courseCollections: CourseCollections,
    private val imageRepository: ImageRepository,
    private val videoRepository: VideoRepository
): ViewModel() {
    val _articleList = mutableStateOf<MutableList<ArticleDataClass>>(mutableListOf())

    fun addNewPost(post: ArticleDataClass) = viewModelScope.launch{
        articleCollections.addArticle(post)
        imageRepository.uploadImage(post)
    }
    fun addNewCourse(course: CourseDataClass) = viewModelScope.launch {
        courseCollections.addCourse(course)
        videoRepository.uploadVideo(course)
    }

    private var isFetching = false
    fun getPost(
        onFinished: (MutableList<ArticleDataClass>) -> Unit
    ){
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                val articleList = articleCollections.getArticle()
                val imageFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for (article in articleList) {
                    val imageFetchDeferred = CompletableDeferred<Unit>()
                    imageFetchDeferreds.add(imageFetchDeferred)

                    getImage(article.articleId) { imageUri ->
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
    ){
        if(!isFetching){
            isFetching = true
            viewModelScope.launch{
                val courseList         = courseCollections.getCourse()
                val videoFetchDeferred = mutableListOf<CompletableDeferred<Unit>>()

                for(course in courseList){
                    val videoDeferred = CompletableDeferred<Unit>()
                    videoFetchDeferred.add(videoDeferred)

                    getVideo(course.courseId){ videoUri ->
                        course.courseContent.add(element = CourseContent(
                            videoTitle       = course.courseName,
                            videoDescription = course.courseName, // need fix
                            videoUri         = videoUri
                        )
                        )
                        videoDeferred.complete(Unit)
                    }
                }
                videoFetchDeferred.awaitAll()

                isFetching = false
                onFinished(courseList)
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

    private fun getVideo(
        courseId: String,
        onFinished: (Uri) -> Unit
    ){
        viewModelScope.launch {
            val videoUri = videoRepository.getVideo(courseId)
            onFinished(videoUri)
        }
    }
}