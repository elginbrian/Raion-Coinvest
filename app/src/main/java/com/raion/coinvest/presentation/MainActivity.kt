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
import com.raion.coinvest.presentation.communitySection.CommunityCreatePost
import com.raion.coinvest.presentation.communitySection.CommunityScreen
import com.raion.coinvest.presentation.communitySection.CommunityViewModel
import com.raion.coinvest.presentation.debugging.DebugScreen3
import com.raion.coinvest.presentation.debugging.DebugScreen5
import com.raion.coinvest.presentation.debugging.DebugViewModel
import com.raion.coinvest.presentation.debugging.DebugViewModel2
import com.raion.coinvest.presentation.designSystem.CoinvestTheme
import com.raion.coinvest.presentation.loginSection.LoginHome
import com.raion.coinvest.presentation.loginSection.LoginViewModel
import com.raion.coinvest.presentation.loginSection.MenuDaftar
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

                NavHost(navController = navController, startDestination = NavigationEnum.LoginScreen.name){
                    composable(NavigationEnum.LoginScreen.name){
                        val viewModel: LoginViewModel by viewModels()
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
                        LoginHome(
                            viewModel = viewModel,
                            onSignInWithEmail = { return@LoginHome viewModel.createUserWithEmail(it.first, it.second) },
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
                            onChangeScreen = { navController.navigate(route = NavigationEnum.CommunityScreen.name) }

                        )
                    }

                    composable(NavigationEnum.RoleSectionScreen.name){
                        MenuDaftar()
                    }

                    composable(NavigationEnum.CommunityScreen.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityScreen(
                            viewModel = viewModel,
                            onTapFloatingButton = { navController.navigate(route = NavigationEnum.CommunityCreatePost.name)}
                        )
                    }

                    composable(NavigationEnum.CommunityCreatePost.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityCreatePost(onUploadPost = {
                            viewModel.addNewPost(it)
                            navController.navigate(route = NavigationEnum.CommunityScreen.name)
                        })
                    }

                    composable(NavigationEnum.DebugScreen3.name){
                        val viewModel: DebugViewModel by viewModels()
                        DebugScreen3(
                            viewModel = viewModel
                        )
                    }

                    composable(NavigationEnum.DebugScreen5.name){
                        val viewModel: DebugViewModel by viewModels()
                        val viewModel2: DebugViewModel2 by viewModels()
                        DebugScreen5(
                            onUploadVideo = { viewModel2.addNewCourse(it) },
                            viewModel     = viewModel,
                            viewModel2    = viewModel2
                        )
                    }
                }
            }
        }
    }
}