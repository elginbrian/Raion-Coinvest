package com.raion.coinvest.presentation.screen.mentorSection

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.screen.debugging.DebugViewModel
import com.raion.coinvest.presentation.screen.userProfileSection.UserProfileTabRow
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.videoPlayerCard.VideoPlayerCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun MentorVideoPlayer(
    onChangeTab: (Int) -> Unit
){
    Scaffold(
        containerColor = CoinvestGrey,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button")
            }
        },
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
            ) {
                VideoPlayerCard()
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.80f),
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
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Text(text = "Lorem Ipsum", fontSize = 32.sp, fontWeight = FontWeight.Bold)

                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ){
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "X Course", fontSize = 12.sp)
                                            }
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "Rp. 69.000", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(12.dp))
                                        Column(modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ){
                                            Text(text = "About", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                            Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "Arrow")
                                        }
                                    }
                                }
                            },
                            content = {
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)){
                                    item {
                                        Spacer(modifier = Modifier.height(140.dp))
                                    }
                                    items(12){
                                        Spacer(modifier = Modifier.padding(8.dp))
                                        CourseCard()
                                    }
                                }
                            },
                            bottomBar = {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                                    AppsBottomBar(currentTab = 1){
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
                                            interactionSource = remember { MutableInteractionSource() } // This is mandatory
                                        ) { },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = CardDefaults.cardColors(CoinvestDarkPurple)
                                ) {
                                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.ChatBubbleOutline, contentDescription = "Add Post", tint = CoinvestBase)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(text = "Chat", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun MVCpreview(){
    MentorVideoPlayer(onChangeTab = {})
}