package com.raion.coinvest.presentation.screen.userProfileSection

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostCard
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun UserProfileScreen(){
    Scaffold(
        containerColor = CoinvestGrey,
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)) {
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.85f),
                contentAlignment = Alignment.TopStart
            ){
                Column(modifier = Modifier) {
                    Spacer(modifier = Modifier.padding(32.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ) {
                        Scaffold(
                            containerColor = CoinvestBase,
                            topBar = {
                                Card(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                                    colors = CardDefaults.cardColors(CoinvestBase)) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, end = 16.dp, start = 16.dp)) {
                                        Spacer(modifier = Modifier.padding(6.dp))
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                            Card(modifier = Modifier
                                                .width(70.dp)
                                                .height(25.dp),
                                                colors = CardDefaults.cardColors(CoinvestDarkPurple),
                                                shape = RoundedCornerShape(50.dp)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                    Text(
                                                        text = "Ikuti", fontSize = 14.sp,
                                                        color = CoinvestBase
                                                    )
                                                }
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(12.dp))
                                        Text(text = "Lorem Ipsum", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                        Text(text = "@Lorem Ipsum", fontSize = 12.sp)

                                        Spacer(modifier = Modifier.padding(8.dp))
                                        Row(modifier = Modifier.width(120.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "Diikuti", fontSize = 12.sp)
                                                Text(text = "XX", fontSize = 10.sp)
                                            }
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "Pengikut", fontSize = 12.sp)
                                                Text(text = "XX", fontSize = 10.sp)
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(4.dp))
                                    }

                                    UserProfileTabRow()
                                }
                            },
                            content = {
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)){
                                    item {
                                        Spacer(modifier = Modifier.height(180.dp))
                                    }
                                    items(12){
                                        Spacer(modifier = Modifier.padding(8.dp))
                                        CommunityPostCard(
                                            articleDataClass = ArticleDataClass(
                                                "",
                                                "",
                                                UserDataClass("", "", "", "", ""),
                                                "",
                                                "",
                                                Uri.EMPTY,
                                            )
                                        ){}
                                    }
                                }
                            },
                            bottomBar = {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                                    AppsBottomBar()
                                }
                            },
                            floatingActionButton = {
                                Card(
                                    modifier = Modifier
                                        .width(106.dp)
                                        .height(46.dp)
                                        .clickable { },
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
                }
                Row() {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Card(modifier = Modifier.size(120.dp),
                        shape = CircleShape
                    ){}
                }

            }

        }
    )
}