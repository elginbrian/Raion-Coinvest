package com.raion.coinvest.presentation.screen.stocksSection

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun StocksScreen(
    viewModel: StocksViewModel,
    onChangeTab: (Int) -> Unit,
    onTabStocks: (Pair<GetTrendingSearchList, String>) -> Unit
){
    val apiResult = remember {
        mutableStateOf(GetTrendingSearchList(coins = listOf()))
    }
    viewModel.getTrendingSearchList { apiResult.value = it }
    if(apiResult.value.coins.isNotEmpty()){
        Log.d("Crypto", apiResult.value.coins[0].item.data.sparkline.toString())
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
                        Text(text = "Stocks Update", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    StocksTabRow(){

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
                item { Spacer(modifier = Modifier.padding(60.dp)) }
                item{
                    Card(modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(150.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        LazyRow(modifier = Modifier.fillMaxSize()){
                            items(apiResult.value.coins.sortedBy { it.item.name }){
                                StocksChartCard(it.item)
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Chart Crypto", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                items(apiResult.value.coins.sortedBy { it.item.marketCapRank }){
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onTabStocks(Pair(apiResult.value, it.item.id)) },

                        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                            Card(modifier = Modifier.size(50.dp)) {
                                AsyncImage(model = it.item.thumb, contentDescription = "thumbnail", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                                Text(text = it.item.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                Text(text = "rank: " + it.item.marketCapRank)
                            }
                        }
                        Row(modifier = Modifier.fillMaxHeight()) {
                            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End) {
                                Text(text = it.item.data.price, fontSize = 16.sp)
                                Text(text = "1 " + it.item.symbol)
                            }
                        }
                    }
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
                AppsBottomBar(currentTab = 2){
                    onChangeTab(it)
                }
            }
        },
    )
}