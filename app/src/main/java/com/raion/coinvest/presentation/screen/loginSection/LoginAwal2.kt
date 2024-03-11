package com.raion.coinvest.presentation.screen.loginSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
@Preview
fun LoginAwal2() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = CoinvestLightGrey),
        contentAlignment = Alignment.Center
    ){
        var checked = remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Card(modifier = Modifier
                .height(310.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(topEnd = 30.dp)),
//                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(CoinvestBase),
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Image(painter = painterResource(id = R.drawable.coinvestdalam), contentDescription = "",)
                    Column(modifier = Modifier
                        .fillMaxWidth()
//                        .padding(15.dp)
                    )
                    {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Lorem Ipsum", fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 32.dp))
                        Text(text = "Lorem Ipsum", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 32.dp))

                    }
                    Image(painter = painterResource(id = R.drawable.selanjutnya), contentDescription = "", modifier = Modifier.padding(15.dp).padding(top = 10.dp).clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) { },)
                }
            }
        }
    }
}