package com.raion.coinvest.presentation.screen.roleSection

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firebaseStorage.model.VerifDataClass
import com.raion.coinvest.data.remote.firestore.UserCollections
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject

@HiltViewModel
class RoleViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val userCollections: UserCollections,
    private val authRepository: EmailAuthRepository
): ViewModel() {
    fun verifUser(verif: VerifDataClass, user: UserDataClass) = viewModelScope.launch {
        imageRepository.uploadVerifImage(verif)
        userCollections.addUsersToFireStore(user)
        authRepository.updateAuthData(user)
        if(user.accountType == "member"){
            imageRepository.uploadProfilePict(user)
        }
    }
}