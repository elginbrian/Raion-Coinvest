package com.raion.coinvest.presentation.screen.newsSection

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.CommentCollections
import com.raion.coinvest.data.remote.firestore.NewsCollections
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val newsRepository: NewsCollections,
    private val commentCollections: CommentCollections
): ViewModel() {

    fun addNews(news: NewsDataClass) = viewModelScope.launch {
        newsRepository.addNews(news)
        imageRepository.uploadNewsImage(news)
    }

    fun addNewComment(comment: CommentDataClass) = viewModelScope.launch {
        commentCollections.addComment(comment)
        imageRepository.uploadCommentImage(comment)
    }

    private var isFetching = false
    fun getNews(
        onFinished: (MutableList<NewsDataClass>) -> Unit
    ){
        if (!isFetching) {
            isFetching = true
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

                isFetching = false
                onFinished(newsList)
            }
        }
    }

    fun getComment(
        onFinished: (MutableList<CommentDataClass>) -> Unit
    ){
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                val commentList = commentCollections.getComment()
                val imageFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for (comment in commentList) {
                    val imageFetchDeferred = CompletableDeferred<Unit>()
                    imageFetchDeferreds.add(imageFetchDeferred)

                    getImage(comment.commentId) { imageUri ->
                        comment.imageUri = imageUri
                        imageFetchDeferred.complete(Unit)
                    }
                }
                imageFetchDeferreds.awaitAll()

                isFetching = false
                onFinished(commentList)
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