package com.raion.coinvest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.auth.GoogleAuthRepository
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.debugging.DebugScreen
import com.raion.coinvest.presentation.debugging.DebugScreen2
import com.raion.coinvest.presentation.debugging.DebugViewModel
import com.raion.coinvest.presentation.designSystem.CoinvestTheme
import com.raion.coinvest.presentation.navigation.NavigationEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthClient by lazy {
        GoogleAuthRepository(
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
                    /* Kalau mau nge-run screen lain, startDestination nya diganti dulu biar ga ngebuka DebugScreen */

                    composable(NavigationEnum.DebugScreen.name){
                        /*
                           Ini composable buat aku (Elgin) ngetest data dari back-end nya,
                            Gaada hubungannya sama mockup UI/UX,
                            nanti pas project kelar bakal dihapus
                        */
                        val viewModel: DebugViewModel by viewModels()
                        val state = viewModel.state.collectAsState().value
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthClient.signInWithGoogle(result.data ?: return@launch)
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )
                        LaunchedEffect(key1 = state.isSignInSuccessful){
                            if(state.isSignInSuccessful){ Toast.makeText(applicationContext, "Sign in success", Toast.LENGTH_LONG).show() }
                        }
                        DebugScreen(
                            viewModel = viewModel,
                            state = state,
                            onSignInWithGoogle = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            },
                            onChangeScreen = { navController.navigate(route = NavigationEnum.DebugScreen2.name) }
                        )
                    }

                    composable(NavigationEnum.DebugScreen2.name){
                        val viewModel: DebugViewModel by viewModels()
                        DebugScreen2(
                            onAddUsersToFireStore = { viewModel.addUsersToFireStore(
                                UserDataClass(
                                    userId = Firebase.auth.currentUser?.uid.orEmpty(),
                                    userName = Firebase.auth.currentUser?.displayName.orEmpty(),
                                    email = Firebase.auth.currentUser?.email.orEmpty(),
                                    accountType = "mentor",
                                    profilePicture = Firebase.auth.currentUser?.photoUrl.toString()
                                )
                            ) }
                        )
                    }
                    /* kalau mau nambah composable baru bisa disini */
                }
            }
        }
    }
}