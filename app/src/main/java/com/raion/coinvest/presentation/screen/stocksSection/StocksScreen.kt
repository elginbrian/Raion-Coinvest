package com.raion.coinvest.presentation.screen.stocksSection

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.data.remote.api.model.Data
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.api.model.GetTrendingStocks
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun StocksScreen(
    viewModel: StocksViewModel,
    onChangeTab: (Int) -> Unit,
    onTapStocks: (Pair<GetTrendingSearchList, String>) -> Unit
){
    val currentTab   = remember { mutableStateOf(0) }
    val cryptoResult = remember { mutableStateOf(GetTrendingSearchList(coins = listOf())) }
    val stockResult  = remember { mutableStateOf(GetTrendingStocks("","", Data(listOf()))) }

    viewModel.getTrendingSearchList { cryptoResult.value = it }
    viewModel.getTrendingStocks { stockResult.value = it }
    Log.d("stock", stockResult.value.toString())

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
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        Text(text = "Stocks Update", fontSize = 16.sp, fontWeight = FontWeight.Bold, color =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    StocksTabRow(){
                        currentTab.value = it
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
                if(currentTab.value == 0){
                    item { Spacer(modifier = Modifier.padding(60.dp)) }
                    item{
                        Card(modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(150.dp),
                            colors = CardDefaults.cardColors(CoinvestBase),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            LazyRow(modifier = Modifier.fillMaxSize()){
                                items(items = cryptoResult.value.coins.sortedBy { it.item.name }, key = {
                                    it.item.id
                                }){
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

                    items(items = cryptoResult.value.coins.sortedBy { it.item.marketCapRank }, key = {
                        it.item.id
                    }){
                        if(it.item.name != "Pepe"){
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) { onTapStocks(Pair(cryptoResult.value, it.item.id)) },

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
                    }
                } else {
                    item { Spacer(modifier = Modifier.padding(50.dp)) }
                    items(items = stockResult.value.data.results.sortedByDescending { stock -> stock.percent }, key = {
                        it.symbol
                    }){
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { },

                            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                                Card(modifier = Modifier.size(50.dp), colors = CardDefaults.cardColors(
                                    CoinvestDarkPurple)) {
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                        Icon(imageVector = Icons.Rounded.Apartment, contentDescription = "company", modifier = Modifier.fillMaxSize(0.7f), tint = CoinvestBase)
                                        AsyncImage(model = it.company.logo, contentDescription = "thumbnail", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                                    }
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(210.dp), verticalArrangement = Arrangement.Center) {
                                    Text(text = it.company.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
                                    Text(text = it.percent.toString()+"%", color = if(it.percent >= 0){
                                        Color(0xFF056927)
                                    } else {
                                        Color(0xFFF00500)
                                    })
                                }
                            }
                            Row(modifier = Modifier.fillMaxHeight()) {
                                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.End) {
                                    Text(text = it.close.toString(), fontSize = 14.sp)
                                    Text(text = it.company.symbol)
                                }
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