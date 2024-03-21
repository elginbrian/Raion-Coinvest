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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
//@Preview
fun IntroScreen2(
    onClickSelanjutnya: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = CoinvestLightGrey),
        contentAlignment = Alignment.Center
    ){
        var checked = remember {
            mutableStateOf(false)
        }
        Image(painter = painterResource(id = R.drawable.introduction), contentDescription = "intro 1", modifier = Modifier.fillMaxSize().scale(1.1f), contentScale = ContentScale.Crop)
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Image(painter = painterResource(id = R.drawable.coinvest_new), contentDescription ="", modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(end = 48.dp))
            Column(modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Belajar Investasi", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Belajar banyak hal dan investasi dengan mentor\n" +
                        "terbaik di sini", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White, textAlign =
                TextAlign.Center)

            }
            Spacer(modifier = Modifier.height(8.dp))
            Image(painter = painterResource(id = R.drawable.dot2), contentDescription = "Dot")
            Spacer(modifier = Modifier.height(8.dp))
            Image(painter = painterResource(id = R.drawable.selanjutnya), contentDescription = "", modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 60.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClickSelanjutnya() },)
        }
    }
}