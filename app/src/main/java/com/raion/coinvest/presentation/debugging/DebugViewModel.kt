package com.raion.coinvest.presentation.debugging

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.api.CoinMarketCapApi
import com.raion.coinvest.data.remote.api.model.GetLatestListingResponse
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.TwitterAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInState
import com.raion.coinvest.data.remote.firestore.UserCollections
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val coinMarketCapApi: CoinMarketCapApi
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
    fun createUserWithEmail()                                                   = viewModelScope.launch { emailAuthRepository.createUser(email = "elginbrian94@gmail.com", password = "220406") }
    fun loginWithEmail()                                                        = viewModelScope.launch { emailAuthRepository.loginUser(email = "elginbrian94@gmail.com", password = "220406") }
    fun createUserWithTwitter(context: Context)                                 = viewModelScope.launch { twitterAuthRepository.createUser(context) }
    fun addUsersToFireStore(user: UserDataClass) = viewModelScope.launch { userCollections.addUsersToFireStore(user) }
    fun getLatestListing(): GetLatestListingResponse?{
        viewModelScope.launch {
            _getLatestListingResponse.value = coinMarketCapApi.getLatestListing()
        }
        return _getLatestListingResponse.value
    }
}