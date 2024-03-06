package com.raion.coinvest.presentation.debugging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import java.time.LocalDateTime
import java.util.UUID

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/

@Composable
fun DebugScreen2(
    onAddUsersToFireStore: (UserDataClass) -> Unit,
    onAddArticleToFireStore: (ArticleDataClass) -> Unit,
    onChangeScreen: () -> Unit
){
    val context = LocalContext.current
    val currentUser = UserDataClass(
        userId = Firebase.auth.currentUser?.uid.orEmpty(),
        userName = Firebase.auth.currentUser?.displayName.orEmpty(),
        email = Firebase.auth.currentUser?.email.orEmpty(),
        accountType = "mentor",
        profilePicture = Firebase.auth.currentUser?.photoUrl.toString()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { onAddUsersToFireStore(currentUser) }) {
            Text(text = "Add Users to FireStore")
        }

        Button(onClick = { onChangeScreen() }) {
            Text(text = "Go to debug screen 3")
        }
    }
}