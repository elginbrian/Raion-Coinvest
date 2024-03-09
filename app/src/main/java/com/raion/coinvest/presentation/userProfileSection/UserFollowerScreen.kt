package com.raion.coinvest.presentation.userProfileSection

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import com.raion.coinvest.presentation.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.communityProfileSection.CommunityAccountCard
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun UserFollowerScreen(){
    val tabIndex = remember {
        mutableStateOf(0)
    }
    Scaffold(
        containerColor = CoinvestBase,
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
            ){
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
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
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button")
                        Text(text = "Username Individu", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                    TabRow(modifier = Modifier.heightIn(32.dp),
                        selectedTabIndex = tabIndex.value,
                        containerColor = Color.Transparent,
                        contentColor = CoinvestBlack
                    ) {
                        Tab(
                            selected = tabIndex.value == 0,
                            onClick = { tabIndex.value = 0 },
                        ){
                            Column {
                                Text(text = "XX Pengikut", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.padding(4.dp))
                            }

                        }
                        Tab(
                            selected = tabIndex.value == 1,
                            onClick = { tabIndex.value = 1 },
                        ){
                            Column {
                                Text(text = "XX Diikuti", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)){
                item {
                    Spacer(modifier = Modifier.padding(50.dp))
                    SearchBar()
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider()
                }
                items(12){
                    Spacer(modifier = Modifier.padding(8.dp))
                    CommunityAccountCard()
                }
                item{
                    Spacer(modifier = Modifier.padding(50.dp))
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                AppsBottomBar()
            }
        }
    )
}