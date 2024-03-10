package com.raion.coinvest.presentation.screen.loginSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        var checked = remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ){
            Image(painter = painterResource(id = R.drawable.coinvesttengah), contentDescription ="" )
            Text(text = "Lorem Ipsum", fontSize = 28.sp, fontWeight = FontWeight.Bold,)
            Text(text = "Lorem Ipsum", fontSize = 14.sp, fontWeight = FontWeight.Bold,)
            Spacer(modifier = Modifier.height(250.dp))
            Image(painter = painterResource(id = R.drawable.logintengah3), contentDescription ="", modifier = Modifier.padding(40.dp) )
        }
    }
}