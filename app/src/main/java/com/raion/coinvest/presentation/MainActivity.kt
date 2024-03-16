package com.raion.coinvest.presentation

import NewsReplying
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.auth.GoogleAuthRepository
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.screen.communitySection.CommunityCreatePost
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostReply
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostReplying
import com.raion.coinvest.presentation.screen.communitySection.CommunityScreen
import com.raion.coinvest.presentation.screen.communitySection.CommunityViewModel
import com.raion.coinvest.presentation.screen.debugging.DebugScreen3
import com.raion.coinvest.presentation.screen.debugging.DebugScreen5
import com.raion.coinvest.presentation.screen.debugging.DebugViewModel
import com.raion.coinvest.presentation.screen.debugging.DebugViewModel2
import com.raion.coinvest.presentation.designSystem.CoinvestTheme
import com.raion.coinvest.presentation.screen.loginSection.LoginHome
import com.raion.coinvest.presentation.screen.loginSection.LoginViewModel
import com.raion.coinvest.presentation.screen.loginSection.MenuDaftar
import com.raion.coinvest.presentation.navigation.CoinvestUserFlow
import com.raion.coinvest.presentation.screen.communityProfileSection.CommunityFollowerScreen
import com.raion.coinvest.presentation.screen.communityProfileSection.CommunityProfileScreen
import com.raion.coinvest.presentation.screen.communitySearchSection.CommunitySearchGrid
import com.raion.coinvest.presentation.screen.communitySearchSection.CommunitySearchScreen
import com.raion.coinvest.presentation.screen.homeSection.DashboardAwal
import com.raion.coinvest.presentation.screen.mentorSection.MentorCreate
import com.raion.coinvest.presentation.screen.mentorSection.MentorNew
import com.raion.coinvest.presentation.screen.mentorSection.MentorScreen
import com.raion.coinvest.presentation.screen.mentorSection.MentorSearchScreen
import com.raion.coinvest.presentation.screen.mentorSection.MentorVideoPlayer
import com.raion.coinvest.presentation.screen.mentorSection.MentorViewModel
import com.raion.coinvest.presentation.screen.newsSection.NewsCreate
import com.raion.coinvest.presentation.screen.newsSection.NewsPage
import com.raion.coinvest.presentation.screen.newsSection.NewsReply
import com.raion.coinvest.presentation.screen.newsSection.NewsScreen
import com.raion.coinvest.presentation.screen.newsSection.NewsViewModel
import com.raion.coinvest.presentation.screen.stocksSection.StocksPage
import com.raion.coinvest.presentation.screen.stocksSection.StocksScreen
import com.raion.coinvest.presentation.screen.stocksSection.StocksViewModel
import com.raion.coinvest.presentation.screen.userProfileSection.UserFollowerScreen
import com.raion.coinvest.presentation.screen.userProfileSection.UserProfileScreen
import com.raion.coinvest.presentation.screen.userProfileSection.UserViewModel
import com.raion.coinvest.presentation.widget.transparentSystemBar.TransparentSystemBar
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
                TransparentSystemBar()
                // NavGraph
                val navController = rememberNavController()

                val entryPointList = listOf(
                    CoinvestUserFlow.HomeScreen.name,      // entry point 0
                    CoinvestUserFlow.MentorScreen.name,    // entry point 1
                    CoinvestUserFlow.StocksScreen.name,    // entry point 2
                    CoinvestUserFlow.CommunityScreen.name, // entry point 3
                    CoinvestUserFlow.NewsScreen.name,      // entry point 4
                )
                var selectedUser = remember {
                    mutableStateOf(UserDataClass("","","","",""))
                }

                NavHost(navController = navController, startDestination =
                    if(Firebase.auth.currentUser != null) {
                        entryPointList[3]
                    } else {
                        CoinvestUserFlow.LoginScreen.name
                    }
                ){
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



                    // tabIndex 0 entry point
                    composable(CoinvestUserFlow.HomeScreen.name){
                        DashboardAwal()
                    }



                    // tabIndex 1 entry point
                    var courseId: String = ""
                    var newCourse: CourseDataClass = CourseDataClass("","","","","",
                        UserDataClass("","","","",""), mutableListOf()
                    )
                    var courseList: MutableList<CourseDataClass> = mutableListOf()
                    composable(CoinvestUserFlow.MentorScreen.name){
                        val viewModel: MentorViewModel by viewModels()
                        MentorScreen(
                            viewModel = viewModel,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) },
                            onOpenCourse = {
                                courseList = it.first
                                courseId = it.second
                                navController.navigate(route = CoinvestUserFlow.MentorVideoPlayer.name)
                            },
                            onTapFloatingButton = { navController.navigate(CoinvestUserFlow.MentorNew.name) },
                            onTapSearch = { navController.navigate(CoinvestUserFlow.MentorSearchScreen.name )}
                        )
                    }
                    composable(CoinvestUserFlow.MentorSearchScreen.name){
                        MentorSearchScreen()
                    }
                    composable(CoinvestUserFlow.MentorVideoPlayer.name){
                        val viewModel: MentorViewModel by viewModels()
                        MentorVideoPlayer(
                            viewModel   = viewModel,
                            courseId    = courseId,
                            courseList  = courseList,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
                        )
                    }
                    composable(CoinvestUserFlow.MentorNew.name){
                        MentorNew(
                            onTapLanjutkan = {
                                newCourse = it
                                navController.navigate(CoinvestUserFlow.MentorCreate.name)
                            }
                        )
                    }
                    composable(CoinvestUserFlow.MentorCreate.name){
                        val viewModel: MentorViewModel by viewModels()
                        MentorCreate(
                            viewModel = viewModel,
                            newCourse = newCourse,
                            onTapPost = {
                                viewModel.addCourse(it)
                                navController.navigate(CoinvestUserFlow.MentorScreen.name)
                            }
                        )
                    }



                    // tabIndex 2 entry point
                    var stocksId: String = ""
                    var stocksList: GetTrendingSearchList = GetTrendingSearchList(listOf())
                    composable(CoinvestUserFlow.StocksScreen.name){
                        val viewModel: StocksViewModel by viewModels()
                        StocksScreen(
                            viewModel = viewModel,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) },
                            onTapStocks = {
                                stocksId = it.second
                                stocksList = it.first
                                navController.navigate(CoinvestUserFlow.StocksPage.name)
                            }
                        )
                    }
                    composable(CoinvestUserFlow.StocksPage.name){
                        StocksPage(
                            stocksId = stocksId,
                            stocksList = stocksList
                        )
                    }



                    // tabIndex 3 entry point
                    var articleId: String = ""
                    var articleList: MutableList<PostDataClass> = mutableListOf()
                    composable(CoinvestUserFlow.CommunityScreen.name){
                        val viewModel: CommunityViewModel by viewModels()
                        CommunityScreen(
                            viewModel = viewModel,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) },
                            onTapFloatingButton = { navController.navigate(route = CoinvestUserFlow.CommunityCreatePost.name)},
                            onTapPost = {
                                articleList = it.first
                                articleId   = it.second
                                navController.navigate(route = CoinvestUserFlow.CommunityPostReply.name)
                            },
                            onTapSearch = { navController.navigate(CoinvestUserFlow.CommunitySearchScreen.name) },
                            onTapProfile = {
                                selectedUser.value = it
                                navController.navigate(CoinvestUserFlow.UserProfileScreen.name)
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
                            onTapPost = { navController.navigate(route = CoinvestUserFlow.CommunityPostReplying.name) },
                            onTapProfile = {
                                selectedUser.value = it
                                navController.navigate(CoinvestUserFlow.UserProfileScreen.name)
                            }
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
                            },
                            onTapProfile = {
                                selectedUser.value = it
                                navController.navigate(CoinvestUserFlow.UserProfileScreen.name)
                            }
                        )
                    }
                    composable(CoinvestUserFlow.CommunitySearchScreen.name){
                        CommunitySearchScreen(
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
                        )
                    }
                    composable(CoinvestUserFlow.CommunitySearchGrid.name){
                        CommunitySearchGrid(
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
                        )
                    }
                    composable(CoinvestUserFlow.CommunityProfileScreen.name){
                        CommunityProfileScreen(
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
                        )
                    }
                    composable(CoinvestUserFlow.CommunityFollowerScreen.name){
                        CommunityFollowerScreen(
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
                        )
                    }



                    // tabIndex 4 entry point
                    var newsId: String = ""
                    var newsList: MutableList<NewsDataClass> = mutableListOf()
                    composable(CoinvestUserFlow.NewsScreen.name){
                        val viewModel: NewsViewModel by viewModels()
                        NewsScreen(
                            viewModel = viewModel,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) },
                            onTabFloatingButton = { navController.navigate(route = CoinvestUserFlow.NewsCreate.name) },
                            onTapNewsCard = {
                                newsList = it.first
                                newsId = it.second
                                navController.navigate(CoinvestUserFlow.NewsPage.name)
                            },
                            onTapSearch = { /* NEWS SEARCH*/ }
                        )
                    }
                    composable(CoinvestUserFlow.NewsPage.name){
                        val viewModel: NewsViewModel by viewModels()
                        NewsPage(
                            viewModel = viewModel,
                            newsList = newsList,
                            newsId = newsId,
                            onClickComment = {
                                newsId = it
                                navController.navigate(route = CoinvestUserFlow.NewsReply.name)
                            },
                            onTapProfile = {
                                selectedUser.value = it
                                navController.navigate(CoinvestUserFlow.UserProfileScreen.name)
                            }
                        )
                    }
                    composable(CoinvestUserFlow.NewsCreate.name){
                        val viewModel: NewsViewModel by viewModels()
                        NewsCreate(onCreateNews = {
                            viewModel.addNews(it)
                            navController.navigate(route = entryPointList[4])
                        } )
                    }
                    composable(CoinvestUserFlow.NewsReply.name){
                        val viewModel: NewsViewModel by viewModels()
                        NewsReply(
                            newsId = newsId,
                            viewModel = viewModel,
                            onTapFloatingButton = { navController.navigate(CoinvestUserFlow.NewsReplying.name) },
                            onTapProfile = {
                                selectedUser.value = it
                                navController.navigate(CoinvestUserFlow.UserProfileScreen.name)
                            }
                        )
                    }
                    composable(CoinvestUserFlow.NewsReplying.name){
                        val viewModel: NewsViewModel by viewModels()
                        NewsReplying(parentId = newsId, onUploadPost = {
                            viewModel.addNewComment(it)
                            navController.navigate(route = CoinvestUserFlow.NewsReply.name)
                        })
                    }



                    composable(CoinvestUserFlow.UserProfileScreen.name){
                        val viewModel: UserViewModel by viewModels()
                        UserProfileScreen(
                            viewModel = viewModel,
                            onChangeTab = { navController.navigate(route = entryPointList[it]) },
                            user = selectedUser.value
                        )
                    }
                    composable(CoinvestUserFlow.UserFollowerScreen.name){
                        UserFollowerScreen(
                            onChangeTab = { navController.navigate(route = entryPointList[it]) }
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