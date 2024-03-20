package com.raion.coinvest.presentation.screen.communitySearchSection

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
@Preview
fun CommunitySearchCard(){
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(150.dp),
        colors   = CardDefaults.cardColors(if(isSystemInDarkTheme()){
            CoinvestBlack
        } else {
            CoinvestLightGrey
        })
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Card(modifier = Modifier.size(50.dp)) {
                AsyncImage(model = "", contentDescription = "profile picture")
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "Lorem Ipsum", fontSize = 12.sp, color = if(isSystemInDarkTheme()){
                CoinvestBase
            } else {
                CoinvestBlack
            })
            Spacer(modifier = Modifier.padding(6.dp))
            Card(modifier = Modifier
                .width(60.dp)
                .height(20.dp),
                colors = CardDefaults.cardColors(CoinvestDarkPurple),
                shape = RoundedCornerShape(50.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "Ikuti", fontSize = 12.sp,
                        color = CoinvestBase
                    )
                }
            }

        }
    }
}