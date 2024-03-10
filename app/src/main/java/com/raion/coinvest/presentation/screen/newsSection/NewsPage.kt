package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun NewsPage(){
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button")

                    Icon(imageVector = Icons.Rounded.Flag, contentDescription = "Back button")
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item{
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp), contentAlignment = Alignment.BottomCenter) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                            ){

                            }
                        }
                        Card(modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(160.dp),
                            colors = CardDefaults.cardColors(Color.Transparent),
                            shape  = RoundedCornerShape(20.dp),
                            //border = BorderStroke(1.dp, CoinvestLightGrey),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ){
                            Box(modifier = Modifier.fillMaxSize()){
                                Image(painter = painterResource(id = R.drawable.blur_background), contentDescription = "blur", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(text = "Minggu, xx November 2024", fontSize = 12.sp)
                                    Text(text = "Lorem Ipsum", fontSize = 18.sp)
                                    Text(text = "Diunggah pada xx menit yang lalu", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(modifier = Modifier.size(32.dp), shape = CircleShape) {

                            }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = "Lorem Ipsum", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Icon(painter = painterResource(id = R.drawable.like_icon), contentDescription = "Like", modifier = Modifier.scale(0.7f))
                        Icon(painter = painterResource(id = R.drawable.comment_icon), contentDescription = "Comment", modifier = Modifier.scale(0.7f))
                        Icon(painter = painterResource(id = R.drawable.bookmark_icon), contentDescription = "Bookmark", modifier = Modifier.scale(0.7f))
                        Icon(painter = painterResource(id = R.drawable.share_icon), contentDescription = "Share", modifier = Modifier.scale(0.7f))
                    }
                }

                item() {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)) {
                        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ut nunc leo. Vestibulum lacus nisi, bibendum quis nulla a, auctor suscipit erat. Phasellus consequat lacus vitae vehicula pulvinar. Curabitur a libero dolor. Ut laoreet orci eget elit elementum, at cursus nisi faucibus. In sit amet eleifend lectus. Sed luctus congue nibh, eget vulputate sapien efficitur id. Integer vel consequat est. Fusce eleifend laoreet ex, vel faucibus enim. Pellentesque vitae elementum enim, vitae condimentum nulla. Mauris facilisis enim sed felis ultrices vehicula. Fusce dapibus sem sit amet commodo iaculis. Aenean aliquet nibh maximus enim suscipit tempus.\n" +
                                "\n" +
                                "Pellentesque fermentum dolor pulvinar, commodo est non, scelerisque diam. Maecenas nec rhoncus lectus, convallis vehicula sem. Etiam elementum neque ut magna feugiat, in lobortis eros lobortis. Proin volutpat sit amet augue at tempor. Proin condimentum nulla massa, eu accumsan ipsum scelerisque ac. Praesent a purus ligula. Donec lacus sem, pretium a pellentesque a, interdum a arcu. Aenean quis nunc eleifend, vulputate massa a, scelerisque enim. Proin ullamcorper mattis elit a pellentesque. Nam egestas tellus elementum eros eleifend, eget pulvinar ligula bibendum. Donec vel ex pretium, elementum urna vitae, blandit lacus. Vestibulum porttitor diam quis urna facilisis facilisis sit amet ut sem. Vestibulum neque nibh, mattis a elementum eu, fringilla et lacus. Praesent laoreet auctor aliquam."
                            , modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Justify)
                    }
                    Spacer(modifier = Modifier.padding(40.dp))
                }
            }
        }
    )
}