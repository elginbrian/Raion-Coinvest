package com.raion.coinvest.presentation.debugging

import androidx.lifecycle.ViewModel
import com.raion.coinvest.model.remote.SignInResult
import com.raion.coinvest.model.remote.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/
class DebugViewModel: ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update {it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState(){
        _state.update { SignInState() }
    }
}