package com.raion.coinvest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.raion.coinvest.model.remote.GoogleAuthClient
import com.raion.coinvest.presentation.debugging.DebugScreen
import com.raion.coinvest.presentation.debugging.DebugViewModel
import com.raion.coinvest.presentation.designSystem.CoinvestTheme
import com.raion.coinvest.presentation.navigation.NavigationEnum
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinvestTheme {

                // NavGraph
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavigationEnum.DebugScreen.name){
                    /* Kalau mau nge-run screen lain, startDestination nya diganti dulu biar ga ngebuka DebugScreen*/

                    composable(NavigationEnum.DebugScreen.name){
                        /*
                           Ini composable buat aku (Elgin) ngetest data dari back-end nya,
                            Gaada hubungannya sama mockup UI/UX,
                            nanti pas project kelar bakal dihapus
                        */
                        val viewModel = viewModel<DebugViewModel>()
                        val state = viewModel.state.collectAsState().value
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthClient.signInWithIntent(
                                            result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        LaunchedEffect(key1 = state.isSignInSuccessful){
                            if(state.isSignInSuccessful){
                                Toast.makeText(applicationContext, "Sign in success", Toast.LENGTH_LONG).show()
                            }
                        }
                        DebugScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }

                    /* kalau mau nambah composable baru bisa disini */
                }
            }
        }
    }
}