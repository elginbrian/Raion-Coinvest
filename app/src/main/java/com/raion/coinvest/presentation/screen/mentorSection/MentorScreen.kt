package com.raion.coinvest.presentation.screen.mentorSection

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.screen.communitySection.CommunityTabRow
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MentorScreen(
    viewModel: MentorViewModel,
    onChangeTab: (Int) -> Unit,
    onTapSearch: () -> Unit,
    onTapFloatingButton: () -> Unit,
    onOpenCourse: (Pair<MutableList<CourseDataClass>, String>) -> Unit
){
    val tabIndex = remember {
        mutableStateOf(0)
    }
    val courseList  = remember { mutableStateOf<MutableList<CourseDataClass>>(mutableListOf()) }
    viewModel.getCourse {
        courseList.value = it
        Log.d("", courseList.value.toString())
    }

    Scaffold(
        topBar = {
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint = if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                        Text(text = "Mentor Untukmu", fontSize = 16.sp, fontWeight = FontWeight.Bold, color =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    SearchBar(){
                        onTapSearch()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    MentorTabRow2(){
                        tabIndex.value = it
                    }
                }
            }
        },
        content = {
                  LazyColumn(modifier = Modifier
                      .fillMaxSize()
                      .padding(16.dp)){
                      item{
                          Spacer(modifier = Modifier.padding(72.dp))
                      }

                      item {
                          Row(
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .height(32.dp)
                                  .padding(end = 100.dp),
                              horizontalArrangement = Arrangement.SpaceBetween,
                              verticalAlignment     = Alignment.CenterVertically
                          ){
                              MentorTabRow()
                          }
                      }
                      item {
                          if(courseList.value.isEmpty()){
                              Spacer(modifier = Modifier.height(8.dp))
                              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                  CircularProgressIndicator(color = CoinvestDarkPurple)
                              }
                          }
                      }
                      if(tabIndex.value == 0){
                          items(courseList.value){
                              Spacer(modifier = Modifier.padding(8.dp))
                              MentorCard(it){
                                  onOpenCourse(Pair(courseList.value, it))
                              }
                          }
                      } else {
                          items(courseList.value){
                              Spacer(modifier = Modifier.padding(8.dp))
                              CourseCard(it){
                                  onOpenCourse(Pair(courseList.value, it))
                              }
                          }
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
                    ) { onTapFloatingButton() },
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