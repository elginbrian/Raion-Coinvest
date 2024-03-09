package com.raion.coinvest.presentation.HomeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.Pink80
import com.raion.coinvest.presentation.transparentTextField.TransparentTextField


@Composable
@Preview
fun DashboardAwal(
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Pink80)){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start) {
//                            Spacer(modifier = Modifier.width(5.dp))
                            Image(painter = painterResource(id = R.drawable.bellsatu), contentDescription = "", modifier = Modifier.size(35.dp))
                            Spacer(modifier = Modifier.width(250.dp))
                            Text(text = "Author")
                            Spacer(modifier = Modifier.width(10.dp))
                            Card(modifier = Modifier
                                .size(40.dp)
                                .clip(shape = CircleShape), colors = CardDefaults.cardColors(Color.White),) {
                            }
                        }
                        Card(modifier = Modifier
                            .width(315.dp)
                            .height(40.dp),colors = CardDefaults.cardColors(Color.White)) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White, shape = RoundedCornerShape(8.dp)), ){
                                TextField(
                                    value = "",
                                    onValueChange = { },
                                    modifier = Modifier.fillMaxSize())
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(modifier = Modifier
                                .height(35.dp)
                                .width(80.dp), colors = CardDefaults.cardColors(
                                Color.White)){
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                    Text(text = "Diikuti", fontSize = 14.sp, textAlign = TextAlign.Center)
                                }

                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Card(modifier = Modifier
                                .height(35.dp)
                                .width(80.dp), colors = CardDefaults.cardColors(
                                Color.White)){
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                    Text(text = "Trending", fontSize = 14.sp, textAlign = TextAlign.Center)
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Card(modifier = Modifier
                                .height(35.dp)
                                .width(80.dp), colors = CardDefaults.cardColors(
                                Color.White)){
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                    Text(text = "Terbaru", fontSize = 14.sp, textAlign = TextAlign.Center)
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Card(modifier = Modifier
                                .height(35.dp)
                                .width(80.dp), colors = CardDefaults.cardColors(
                                Color.White)){
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                    Text(text = "Rising", fontSize = 14.sp, textAlign = TextAlign.Center)
                                }


                            }

                        }
                    }
                }
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()
                ) {

                    items(5){
                        Spacer(modifier = Modifier.height(16.dp))
//                PostCard(
//                    header   = it.articleTitle,
//                    content  = it.articleContent,
//                    imageUri = it.imageUri
//                )
//                Text(text = "Lorem Ipsum")
                        Card(modifier = Modifier
                            .width(315.dp)
                            .height(150.dp)
                            .padding(10.dp)) {
                        }

//                Log.d("items", it.toString())
                    }
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth()){
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Lanjutkan Coursemu", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(110.dp))
                        Text(text = "Lihat Semua")
                    }
                }
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(0.dp), modifier = Modifier.fillMaxSize()
                ) {

                    items(5){
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(modifier = Modifier
                            .width(180.dp)
                            .height(227.dp)
                            .padding(10.dp)) {
                        }

//                Log.d("items", it.toString())
                    }
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth()){
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Ikuti perkembangan Berita!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(79.dp))
                        Text(text = "Lihat Semua")
                    }
                }
            }
//            val Text = listOf("data:image/jpeg;base64,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", "https://asset-2.tstatic.net/pekanbaru/foto/bank/images/inilah-10-youtuber-gaming-paling-banyak-subscriber-di-indonesia-ada-frost-diamond-hingga-erpan-1140.jpg")
            val Text = listOf("https://image.popmama.com/content-images/post/20211207/erpan-2jpg-de95436b6435fbcad08ae47d19a4fef7.jpg?width=600&height=auto", "https://asset-2.tstatic.net/pekanbaru/foto/bank/images/inilah-10-youtuber-gaming-paling-banyak-subscriber-di-indonesia-ada-frost-diamond-hingga-erpan-1140.jpg", "https://yt3.googleusercontent.com/ytc/AIdro_lIEXPLM6lwAD6CX89UDeMVuKBfCA_NBzMj7wQg4A=s900-c-k-c0x00ffffff-no-rj")
            items(Text){
                Card(modifier = Modifier
                    .height(315.dp)
                    .width(250.dp)) {
                    AsyncImage(model = it, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }
                Spacer(modifier = Modifier.height(4.dp))


            }

        }
    }
}