package com.raion.coinvest.presentation.screen.mentorSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
@Preview
fun MentorCard(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp),
        colors = CardDefaults.cardColors(CoinvestLightGrey)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Card(modifier = Modifier
                .width(110.dp)
                .height(130.dp)) {
                AsyncImage(model = "", contentDescription = "profile picture")
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = "Lorem ipsum", fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Lorem ipsum dolor si amet", fontSize = 12.sp)
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Rp.50000")

                    Card(modifier = Modifier
                        .width(70.dp)
                        .height(25.dp),
                        colors = CardDefaults.cardColors(CoinvestDarkPurple),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(
                                text = "Lihat", fontSize = 14.sp,
                                color = CoinvestBase
                            )
                        }
                    }
                }
            }
        }
    }
}