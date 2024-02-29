package com.raion.coinvest.presentation.debugging

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.model.remote.auth.EmailAuthRepository
import com.raion.coinvest.model.remote.auth.SignInResult
import com.raion.coinvest.model.remote.auth.SignInState
import com.raion.coinvest.model.remote.auth.TwitterAuthRepository
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
    private val twitterAuthRepository: TwitterAuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
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
    fun createUserWithEmail()   = viewModelScope.launch { emailAuthRepository.createUser(email = "elginbrian94@gmail.com", password = "220406") }
    fun createUserWithTwitter(context: Context) = viewModelScope.launch { twitterAuthRepository.createUser(context) }
}