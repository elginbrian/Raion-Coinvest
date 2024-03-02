package com.raion.coinvest.presentation.debugging

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.raion.coinvest.data.local.videoplayer.MetadataReader
import com.raion.coinvest.data.local.videoplayer.VideoDataClass
import com.raion.coinvest.data.remote.api.CoinMarketCapApi
import com.raion.coinvest.data.remote.api.model.GetLatestListingResponse
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.TwitterAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInState
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.VideoRepository
import com.raion.coinvest.data.remote.firestore.ArticleCollections
import com.raion.coinvest.data.remote.firestore.UserCollections
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/
@HiltViewModel
class DebugViewModel @Inject constructor(
    private val emailAuthRepository: EmailAuthRepository,
    private val twitterAuthRepository: TwitterAuthRepository,
    private val userCollections: UserCollections,
    private val articleCollections: ArticleCollections,
    private val coinMarketCapApi: CoinMarketCapApi,
    private val savedStateHandle: SavedStateHandle,
    val  player: Player,
    private val metadata: MetadataReader,
    private val imageRepository: ImageRepository,
    private val videoRepository: VideoRepository
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    private val _getLatestListingResponse = mutableStateOf<GetLatestListingResponse?>(null)
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update {it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
    fun createUserWithEmail()                            = viewModelScope.launch { emailAuthRepository.createUser(email = "elginbrian94@gmail.com", password = "220406") }
    fun loginWithEmail()                                 = viewModelScope.launch { emailAuthRepository.loginUser(email = "elginbrian94@gmail.com", password = "220406") }
    fun createUserWithTwitter(context: Context)          = viewModelScope.launch { twitterAuthRepository.createUser(context) }
    fun addUsersToFireStore(user: UserDataClass)         = viewModelScope.launch { userCollections.addUsersToFireStore(user) }
    fun addArticleToFireStore(article: ArticleDataClass) = viewModelScope.launch { articleCollections.addArticle(article, ) }
    fun addImageToFireStore(imageUri: Uri)               = viewModelScope.launch { imageRepository.uploadImage(imageUri) }
    fun addVideoToFireStore(videoUri: Uri)               = viewModelScope.launch { videoRepository.uploadVideo(videoUri) }


    fun getLatestListing(): GetLatestListingResponse?{
        viewModelScope.launch {
            _getLatestListingResponse.value = coinMarketCapApi.getLatestListing()
        }
        return _getLatestListingResponse.value
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