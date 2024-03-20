package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.NewsCollections
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.screen.mentorSection.MentorTabRow2
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun NewsScreen(
    viewModel: NewsViewModel,
    onChangeTab: (Int) -> Unit,
    onTabFloatingButton: () -> Unit,
    onTapSearch: () -> Unit,
    onTapNewsCard: (Pair<MutableList<NewsDataClass>, String>) -> Unit
){
    val tabIndex = remember {
        mutableStateOf(0)
    }
    val newsList = remember { mutableStateOf<MutableList<NewsDataClass>>(mutableListOf()) }
    viewModel.getNews { newsList.value = it }

    Scaffold(
        topBar    = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                    MaterialTheme.colorScheme.background
                } else {
                    CoinvestBase
                })
            ) {
                Column(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        Text(text = "Informasi dan Berita", fontSize = 16.sp, fontWeight = FontWeight.Bold, color =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }
                    SearchBar(){
                        onTapSearch()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    NewsTabRow(){
                        tabIndex.value = it
                    }
                }
            }
        },
        content   = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    Spacer(modifier = Modifier.padding(74.dp))
                    NewTabRow2()
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                item{
                    Card(modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(150.dp),
                        colors = CardDefaults.cardColors(CoinvestBase)
                    ) {
                        LazyRow(modifier = Modifier.fillMaxSize()){
                            items(items = newsList.value.shuffled(), key = {
                                it.newsId
                            }){
                                Card(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(360.dp).clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) { onTapNewsCard(Pair(newsList.value, it.newsId)) },
                                    colors = CardDefaults.cardColors(CoinvestBase)
                                ) {
                                    AsyncImage(model = it.imageUri, contentDescription = "Display", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                                }
                                Spacer(modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Berita Terbaru", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                item {
                    if(newsList.value.isEmpty()){
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            CircularProgressIndicator(color = CoinvestDarkPurple)
                        }
                    }
                }
                items(items = newsList.value, key = {
                    it.newsId
                }){
                    Spacer(modifier = Modifier.padding(8.dp))
                    NewsCard(newsDataClass = it, onClick = {
                        onTapNewsCard(Pair(newsList.value, it.newsId))
                    })
                }
                item {
                    Spacer(modifier = Modifier.padding(80.dp))
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                AppsBottomBar(currentTab = 4){
                    onChangeTab(it)
                }
            }
        },
        floatingActionButton = {
            Card(
                modifier = Modifier
                    .width(106.dp)
                    .height(46.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onTabFloatingButton() },
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(CoinvestDarkPurple)
            ) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post", tint = CoinvestBase)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Post", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    )
}