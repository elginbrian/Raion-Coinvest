package com.raion.coinvest.presentation.debugging

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raion.coinvest.data.remote.auth.model.SignInState
import com.raion.coinvest.presentation.designSystem.CoinvestSora

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/

@Composable
fun DebugScreen(
    viewModel: DebugViewModel,
    state: SignInState,
    onSignInWithGoogle: () -> Unit,
    onChangeScreen: () -> Unit
    ){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Button(onClick = { onSignInWithGoogle() }) {
            Text(text = "Sign in with Google", fontFamily = CoinvestSora, fontWeight = FontWeight.Normal)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { viewModel.createUserWithEmail() }) {
                Text(text = "Sign in with Email", fontFamily = CoinvestSora, fontWeight = FontWeight.Bold)
            }
            Button(onClick = { viewModel.loginWithEmail() }) {
                Text(text = "Login with Email", fontFamily = CoinvestSora, fontWeight = FontWeight.Medium)
            }
        }

        Button(onClick = { viewModel.createUserWithTwitter(context) }) {
            Text(text = "Sign in with Twitter", fontFamily = CoinvestSora, fontWeight = FontWeight.Light)
        }

        Button(onClick = { onChangeScreen() }) {
            Text(text = "Go to DebugScreen2")
        }

        //val response = viewModel.getLatestListing()
        //Log.d("response", response.toString())
    }
}