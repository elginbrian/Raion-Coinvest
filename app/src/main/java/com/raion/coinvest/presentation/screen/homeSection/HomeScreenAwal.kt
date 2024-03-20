package com.raion.coinvest.presentation.screen.homeSection

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.Pink80
import com.raion.coinvest.presentation.navigation.CoinvestUserFlow
import com.raion.coinvest.presentation.screen.communitySearchSection.CommunitySearchGrid
import com.raion.coinvest.presentation.screen.stocksSection.StocksChartCard
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
fun DashboardAwal(
    onChangeScreen: (String) -> Unit,
    viewModel: HomeViewModel,
    onChangeTab: (Int) -> Unit,
    onClickCourse: (Pair<MutableList<CourseDataClass>, String>) -> Unit,
    onClickNews: (Pair<MutableList<NewsDataClass>, String>) -> Unit,
    onClickProfile: (UserDataClass) -> Unit
) {
    val cryptoResult = remember { mutableStateOf(GetTrendingSearchList(coins = listOf())) }
    viewModel.getTrendingSearchList { cryptoResult.value = it }

    val courseList = remember { mutableStateOf<MutableList<CourseDataClass>>(mutableListOf()) }
    viewModel.getCourse {
        courseList.value = it
        Log.d("", courseList.value.toString())
    }

    val newsList = remember { mutableStateOf<MutableList<NewsDataClass>>(mutableListOf()) }
    viewModel.getNews { newsList.value = it }

    Scaffold(
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        shape = RoundedCornerShape(
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp
                        ),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.backcoinbaru),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
//                            Spacer(modifier = Modifier.width(5.dp))
                                    Card(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(shape = CircleShape),
                                        colors = CardDefaults.cardColors(Color.White),
                                    ) {
                                        AsyncImage(
                                            model = Firebase.auth.currentUser?.photoUrl.toString(),
                                            contentDescription = "profile picture",
                                            modifier = Modifier.fillMaxSize().clickable {
                                                onClickProfile(UserDataClass(
                                                    userId   = Firebase.auth.currentUser?.uid,
                                                    userName = Firebase.auth.currentUser?.displayName,
                                                    email    = Firebase.auth.currentUser?.email,
                                                    profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                                    accountType = ""
                                                ))
                                            },
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "Hi! " + Firebase.auth.currentUser?.displayName.toString(),
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.width(200.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.lonceng),
                                        contentDescription = "",
                                        modifier = Modifier.size(32.dp)
                                    )

                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                SearchBar {

                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(150.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        LazyRow(modifier = Modifier.fillMaxSize().clickable {
                            onChangeScreen(CoinvestUserFlow.StocksScreen.name)
                        }) {
                            items(
                                items = cryptoResult.value.coins.sortedBy { it.item.name },
                                key = {
                                    it.item.id
                                }) {
                                StocksChartCard(it.item)
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Lanjutkan Coursemu",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Text(text = "Lihat Semua", modifier = Modifier.clickable {
                                onChangeScreen(CoinvestUserFlow.MentorScreen.name)
                            })
                        }
                    }
                }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(0.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(courseList.value) {
                            CompactCourseCard(it){
                                onClickCourse(Pair(courseList.value, it))
                            }
                        }
                    }
                }

                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Ikuti perkembangan Berita!",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "Ikuti semua tentang investasi crypto hingga reksadana",
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                items(newsList.value) {
                    CompactNewsCard(news = it){
                        onClickNews(Pair(newsList.value, it))
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(80.dp))
                }
            }
        },
        topBar = {},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                AppsBottomBar(currentTab = 0) {
                    onChangeTab(it)
                }
            }
        },
    )
}
