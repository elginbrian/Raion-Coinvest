package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.screen.mentorSection.MentorTabRow2
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun NewsScreen(
    onChangeTab: (Int) -> Unit
){
    val tabIndex = remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar    = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
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
                        Text(text = "Informasi dan Berita", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    SearchBar()
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
                        .height(150.dp)) {
                        LazyRow(modifier = Modifier.fillMaxSize()){
                            items(5){
                                Card(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(360.dp)) {
                                    Text(text = it.toString())
                                }
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
                items(6){
                    Spacer(modifier = Modifier.padding(8.dp))
                    NewsCard()
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
        }
    )
}

@Composable
@Preview
fun NSpreview(){
    NewsScreen(onChangeTab = {})
}