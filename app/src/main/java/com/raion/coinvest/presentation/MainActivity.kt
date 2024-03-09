package com.raion.coinvest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.raion.coinvest.data.remote.auth.GoogleAuthRepository
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.presentation.communitySection.CommunityCreatePost
import com.raion.coinvest.presentation.communitySection.CommunityPostReply
import com.raion.coinvest.presentation.communitySection.CommunityPostReplying
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
import com.raion.coinvest.presentation.navigation.CoinvestUserFlow
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
                var articleId: String = ""
                var articleList: MutableList<ArticleDataClass> = mutableListOf()

                NavHost(navController = navController, startDestination = CoinvestUserFlow.LoginScreen.name){
                    composable(CoinvestUserFlow.LoginScreen.name){
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
                            onChangeScreen = { navController.navigate(route = CoinvestUserFlow.CommunityScreen.name) }

                        )
                    }

                    composable(CoinvestUserFlow.RoleSectionScreen.name){
                        MenuDaftar()
                    }

                    composable(CoinvestUserFlow.CommunityScreen.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityScreen(
                            viewModel = viewModel,
                            onTapFloatingButton = { navController.navigate(route = CoinvestUserFlow.CommunityCreatePost.name)},
                            onTapPost = {
                                articleList = it.first
                                articleId   = it.second
                                navController.navigate(route = CoinvestUserFlow.CommunityPostReply.name)
                            }
                        )
                    }

                    composable(CoinvestUserFlow.CommunityCreatePost.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityCreatePost(onUploadPost = {
                            viewModel.addNewPost(it)
                            navController.navigate(route = CoinvestUserFlow.CommunityScreen.name)
                        })
                    }

                    composable(CoinvestUserFlow.CommunityPostReply.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityPostReply(
                            articleList = articleList,
                            articleId = articleId,
                            viewModel = viewModel,
                            onTapPost = { navController.navigate(route = CoinvestUserFlow.CommunityPostReplying.name) }
                        )
                    }

                    composable(CoinvestUserFlow.CommunityPostReplying.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityPostReplying(
                            articleList = articleList,
                            articleId = articleId,
                            viewModel = viewModel,
                            onUploadReply = {
                                viewModel.addNewComment(it)
                                navController.navigate(route = CoinvestUserFlow.CommunityPostReply.name)
                            }
                        )
                    }

                    composable(CoinvestUserFlow.DebugScreen3.name){
                        val viewModel: DebugViewModel by viewModels()
                        DebugScreen3(
                            viewModel = viewModel
                        )
                    }

                    composable(CoinvestUserFlow.DebugScreen5.name){
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