package com.raion.coinvest.presentation.screen.loginSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestBorder

@Composable
@Preview
fun LoginAwal3() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = CoinvestBorder),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.bg_layer), contentDescription = "Background",
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ){
            Spacer(modifier = Modifier.height(48.dp))
            Image(painter = painterResource(id = R.drawable.coinvest_new), contentDescription ="", modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(end = 48.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Halo! Selamat Datang", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = "Mari Kita Mulai!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
                Image(painter = painterResource(id = R.drawable.dot1), contentDescription = "Dot")
                Spacer(modifier = Modifier.height(8.dp))
                Image(painter = painterResource(id = R.drawable.logintengah3), contentDescription ="", modifier = Modifier.padding(start = 40.dp, end = 40.dp, bottom = 40.dp).clickable {

                } )
            }
        }
    }
}