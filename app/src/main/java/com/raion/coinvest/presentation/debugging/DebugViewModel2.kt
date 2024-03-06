package com.raion.coinvest.presentation.debugging

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.ArticleCollections
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugViewModel2 @Inject constructor(
    private val articleCollections: ArticleCollections,
    private val imageRepository: ImageRepository
): ViewModel() {
    val _articleList = mutableStateOf<MutableList<ArticleDataClass>>(mutableListOf())

    fun addNewPost(post: ArticleDataClass) = viewModelScope.launch{
        articleCollections.addArticle(post)
        imageRepository.uploadImage(post)
    }

    private var isFetching = false
    fun getPost(completion: (MutableList<ArticleDataClass>) -> Unit) {
        if (!isFetching) {
            isFetching = true
            viewModelScope.launch {
                val articleList = articleCollections.getArticle()
                val imageFetchDeferreds = mutableListOf<CompletableDeferred<Unit>>()

                for (index in articleList) {
                    val imageFetchDeferred = CompletableDeferred<Unit>()
                    imageFetchDeferreds.add(imageFetchDeferred)

                    getImage(index.articleId) { imageUri ->
                        index.imageUri = imageUri
                        imageFetchDeferred.complete(Unit)
                    }
                }

                // Wait for all image fetches to complete
                imageFetchDeferreds.awaitAll()

                isFetching = false
                completion(articleList)
            }
        }
    }

    private fun getImage(articleId: String, completion: (Uri) -> Unit) {
        viewModelScope.launch {
            val imageUri = imageRepository.getImage(articleId)
            completion(imageUri)
        }
    }
}