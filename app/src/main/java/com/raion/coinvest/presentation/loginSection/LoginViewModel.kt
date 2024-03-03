package com.raion.coinvest.presentation.loginSection

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.data.remote.auth.model.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailAuthRepository: EmailAuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    private val _signInResult = mutableStateOf(SignInResult(false, null, null))
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
    fun createUserWithEmail(email: String, password: String): SignInResult {
        viewModelScope.launch {
            val result = emailAuthRepository.createUser(email, password)

            _signInResult.value = _signInResult.value.copy(
                isSuccess = result.isSuccess,
                data = result.data,
                errorMessage = result.errorMessage
            )
        }

        return _signInResult.value
    }
}