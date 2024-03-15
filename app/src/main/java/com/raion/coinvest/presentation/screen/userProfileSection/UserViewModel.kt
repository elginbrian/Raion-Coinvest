package com.raion.coinvest.presentation.screen.userProfileSection

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.CommentCollections
import com.raion.coinvest.data.remote.firestore.LikeCollections
import com.raion.coinvest.data.remote.firestore.PostCollections
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val postCollections: PostCollections,
    private val commentCollections: CommentCollections,
    private val imageRepository: ImageRepository,
    private val likeCollections: LikeCollections
): ViewModel() {
    private var isFetching = false
    private var isFetching2 = false
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

    fun addLike(likeDataClass: LikeDataClass) = viewModelScope.launch { likeCollections.addLike(likeDataClass) }
    fun deleteLike(likeDataClass: LikeDataClass) = viewModelScope.launch { likeCollections.deleteLike(likeDataClass) }
    fun getLike(
        onFinished: (MutableList<LikeDataClass>) -> Unit
    ){
        if(!isFetching2){
            isFetching2 = true
            viewModelScope.launch {
                var resultList = likeCollections.getLike()
                val likeFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for(like in resultList){
                    val likeFetchDeferred = CompletableDeferred<Unit>()
                    likeFetchDeferreds.add(likeFetchDeferred)
                    likeFetchDeferred.complete(Unit)
                }

                likeFetchDeferreds.awaitAll()

                isFetching2 = false
                onFinished(resultList)
            }
        }
    }
}