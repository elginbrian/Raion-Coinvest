package com.raion.coinvest.presentation.screen.mentorSection

import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.raion.coinvest.data.local.exoPlayer.MetadataReader
import com.raion.coinvest.data.local.exoPlayer.model.VideoDataClass
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.CourseCollections
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorViewModel @Inject constructor(
    private val courseCollections: CourseCollections,
    private val videoRepository: VideoRepository,
    private val savedStateHandle: SavedStateHandle,
    var player: Player,
    private val metadata: MetadataReader,
): ViewModel() {
    private var isFetching = false

    fun addCourse(course: CourseDataClass) = viewModelScope.launch {
        courseCollections.addCourse(course)
        videoRepository.uploadVideo(course)
    }

    fun getCourse(
        onFinished: (MutableList<CourseDataClass>) -> Unit
    ) {
        if (!isFetching) {
            isFetching = true

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
                isFetching = false
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

    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())
    val videoItems = videoUris.map {uris ->
        uris.map { uri ->
            VideoDataClass(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name = metadata.getMetaDataFromUri(uri)?.fileName ?: "No name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri){
        savedStateHandle["videoUris"] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri){
        player.setMediaItem(
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}