package com.raion.coinvest.presentation.debugging

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.raion.coinvest.model.remote.SignInState

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/

@Composable
fun DebugScreen(
    state: SignInState,
    onSignInClick: () -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = { onSignInClick() }) {
            Text(text = "Sign in")
        }
    }
}