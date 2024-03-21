package com.raion.coinvest.presentation.screen.stocksSection

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StocksPage(
    stocksId: String,
    stocksList: GetTrendingSearchList,
    onTapBack: () -> Unit
){
    val thisStocks = stocksList.coins.filter { coin -> coin.item.id.equals(stocksId) }
    val amount = remember { mutableDoubleStateOf(1.0) }
    val thisStockPrice = remember { mutableStateOf(thisStocks[0].item.data.price.replace("$", "").replace(",","").toDouble() * amount.value) }

    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                    MaterialTheme.colorScheme.background
                } else {
                    CoinvestBase
                })
            ){
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
                        }, modifier = Modifier.clickable { onTapBack() })
                        Text(text = thisStocks[0].item.symbol, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search", tint = if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)){
                item {
                    Spacer(modifier = Modifier.padding(40.dp))
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = thisStocks[0].item.name+" Price", fontSize = 14.sp, fontWeight = FontWeight.Normal)
                        Text(text = thisStocks[0].item.data.price, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                            MaterialTheme.colorScheme.background
                        } else {
                            CoinvestBase
                        }),
                        border = BorderStroke(1.dp, CoinvestBorder)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()){
                            Image(painter = painterResource(id = R.drawable.grid), contentDescription = "grid", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(thisStocks[0].item.data.sparkline)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build(),
                                contentDescription = "sparkline",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Estimasi Nilai", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = CoinvestDarkPurple)
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Text(text = thisStocks[0].item.symbol, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Card(modifier = Modifier
                                .width(160.dp)
                                .height(48.dp),
                                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                    MaterialTheme.colorScheme.background
                                } else {
                                    CoinvestBase
                                }),
                                border = BorderStroke(1.dp, CoinvestBorder)
                            ){
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)) {
                                    TransparentTextField(text = amount.value.toString(), onValueChange = {
                                        if(it.isNotEmpty()){
                                            amount.value = 0.0
                                            amount.value = it.toDouble()
                                            thisStockPrice.value = thisStocks[0].item.data.price.replace("$", "").replace(",","").toDouble() * it.toDouble()
                                        } else {
                                            amount.value = 0.0
                                            thisStockPrice.value = thisStocks[0].item.data.price.replace("$", "").replace(",","").toDouble() * 0.0
                                        }
                                    }, onFocusChange = {})
                                }
                            }
                        }

                        Column() {
                            Text(text = "USD", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Card(modifier = Modifier
                                .width(160.dp)
                                .height(48.dp),
                                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                    MaterialTheme.colorScheme.background
                                } else {
                                    CoinvestBase
                                }),
                                border = BorderStroke(1.dp, CoinvestBorder)
                            ){
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)) {
                                    TransparentTextField(text = thisStockPrice.value.toString(), onValueChange = {}, onFocusChange = {})
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                            MaterialTheme.colorScheme.background
                        } else {
                            CoinvestBase
                        }),
                        border = BorderStroke(1.dp, CoinvestBorder)){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 16.dp, horizontal = 24.dp)) {
                            Text(text = "Statistik", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                CoinvestBase
                            } else {
                                CoinvestBlack
                            })
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                , horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(modifier = Modifier) {
                                    Text(text = "Market Cap", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.data.marketCap, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                                Column(modifier = Modifier, horizontalAlignment = Alignment.End) {
                                    Text(text = "Total Volume", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.data.totalVolume, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                            }

                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                , horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(modifier = Modifier) {
                                    Text(text = "Market Cap Rank", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.marketCapRank.toString(), fontSize = 14.sp, fontWeight = FontWeight.Normal, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                                Column(modifier = Modifier, horizontalAlignment = Alignment.End) {
                                    Text(text = "Score", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.score.toString(), fontSize = 14.sp, fontWeight = FontWeight.Normal, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                            }

                            Spacer(modifier = Modifier.padding(4.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                , horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(modifier = Modifier) {
                                    Text(text = "Market Cap BTC", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.data.marketCapBTC+"                 ", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                                Column(modifier = Modifier, horizontalAlignment = Alignment.End) {
                                    Text(text = "Price BTC", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisStocks[0].item.data.priceBTC, fontSize = 14.sp, fontWeight = FontWeight.Normal, maxLines = 1, color = if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.padding(40.dp)) }
            }
        }
    )
}