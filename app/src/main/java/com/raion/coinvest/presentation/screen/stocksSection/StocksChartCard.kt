package com.raion.coinvest.presentation.screen.stocksSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.raion.coinvest.data.remote.api.model.CoinDetails
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
//@Preview
fun StocksChartCard(
    coins: CoinDetails
){
    Card(modifier = Modifier
        .width(330.dp)
        .height(150.dp),
        colors = CardDefaults.cardColors(CoinvestLightGrey),
        shape = RectangleShape
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        , contentAlignment = Alignment.TopCenter){
            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween) {
                Card(modifier = Modifier
                    .width(60.dp)
                    .height(30.dp),
                    colors = CardDefaults.cardColors(CoinvestDarkPurple)

                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(
                            text = "Chart", fontSize = 16.sp,
                            color = CoinvestBase,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
                            Text(text = coins.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            Text(text = "rank: " + coins.marketCapRank)
                        }
                    }
                    Row(modifier = Modifier) {
                        Column(modifier = Modifier, verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End) {
                            Text(text = coins.data.price, fontSize = 14.sp)
                            Text(text = "1 " + coins.symbol)
                        }
                    }
                }
            }
            Card(modifier = Modifier
                .width(180.dp)
                .height(100.dp),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coins.data.sparkline)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "sparkline",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}