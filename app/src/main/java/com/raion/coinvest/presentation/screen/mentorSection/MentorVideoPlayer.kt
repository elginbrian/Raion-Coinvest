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
import androidx.compose.foundation.lazy.items
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
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
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
    viewModel: MentorViewModel,
    courseId: String,
    courseList: MutableList<CourseDataClass>,
    onChangeTab: (Int) -> Unit
){
    val thisCourse = courseList.filter { course -> course.courseId.equals(courseId) }


    Scaffold(
        containerColor = CoinvestBlack,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint = Color.White)
            }
        },
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.34f)
            ) {
                VideoPlayerCard(viewModel = viewModel, videoUri =  thisCourse[0].courseContent[0].videoUri)
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.82f),
                contentAlignment = Alignment.TopStart
            ){
                Column(modifier = Modifier) {
                    Spacer(modifier = Modifier.padding(33.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ) {
                        Scaffold(
                            containerColor = CoinvestBase,
                            topBar = {
                                Card(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                                    colors = CardDefaults.cardColors(CoinvestBase)) {

                                }
                            },
                            content = {
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)){
                                    item {
                                        Column(modifier = Modifier
                                            .fillMaxWidth()) {
                                            Spacer(modifier = Modifier.padding(4.dp))
                                            Text(text = thisCourse[0].courseName, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                                            Spacer(modifier = Modifier.padding(4.dp))
                                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Text(text = thisCourse[0].courseOwner.userName.toString(), fontSize = 14.sp)
                                                }
                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Text(text = "Rp."+thisCourse[0].coursePrice, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(8.dp))
                                            Column(modifier = Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ){
                                                Text(text = "About", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                                Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "Arrow")
                                            }
                                        }
                                    }
                                    items(courseList){
                                        Spacer(modifier = Modifier.padding(8.dp))
                                        CourseCard(it){

                                        }
                                    }
                                    item {
                                        Spacer(modifier = Modifier.height(180.dp))
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