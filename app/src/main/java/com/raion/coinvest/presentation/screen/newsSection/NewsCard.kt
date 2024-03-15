package com.raion.coinvest.presentation.screen.newsSection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase

@Composable
//@Preview
fun NewsCard(
    onClick: () -> Unit,
    newsDataClass: NewsDataClass
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp).clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(model = newsDataClass.imageUri, contentDescription = "NewsPhoto", modifier = Modifier.fillMaxSize().blur(radiusX = 2.dp, radiusY = 2.dp), contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = newsDataClass.newsTitle, color = CoinvestBase, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = newsDataClass.newsAuthor.userName.toString(), color = CoinvestBase, fontWeight = FontWeight.Normal)
                    Text(text = newsDataClass.newsCreatedAt.substring(0,10), color = CoinvestBase, fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}