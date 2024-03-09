package com.raion.coinvest.presentation.communitySection

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.ArticleCollections
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val articleCollections: ArticleCollections,
    private val imageRepository: ImageRepository
): ViewModel() {
    fun addNewPost(post: ArticleDataClass) = viewModelScope.launch{
        articleCollections.addArticle(post)
        imageRepository.uploadImage(post)
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