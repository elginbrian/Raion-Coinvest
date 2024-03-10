package com.raion.coinvest.presentation.screen.newsSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
@Preview
fun NewsCard(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)) {
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(model = "", contentDescription = "NewsPhoto", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Lorem Ipsum")
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Lorem Ipsum")
                    Text(text = "Minggu, XX November 2024")
                }
            }
        }
    }
}