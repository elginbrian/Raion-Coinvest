package com.raion.coinvest.presentation.screen.homeSection

import android.annotation.SuppressLint
import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.Pink80
import com.raion.coinvest.presentation.screen.communitySearchSection.CommunitySearchGrid
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
fun DashboardAwal(
){
    Scaffold (
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)){
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    ,verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Card(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                    shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                                ){
                                    Image(painter = painterResource(id = R.drawable.backcoinbaru), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp) // Ubah tinggi Box sesuai kebutuhan Anda
                                    .padding(16.dp), // Tambahkan padding jika diperlukan
                                contentAlignment = Alignment.Center // Posisi konten di tengah Box
                            ) {
                                Spacer(modifier = Modifier.height(50.dp))
                                Column(modifier = Modifier.fillMaxSize().padding(top = 37.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start) {
//                            Spacer(modifier = Modifier.width(5.dp))
                                        Card(modifier = Modifier
                                            .size(40.dp)
                                            .clip(shape = CircleShape), colors = CardDefaults.cardColors(Color.White),) {
                                        }
                                        Spacer(modifier = Modifier.width(15.dp))
                                        Text(
                                            text = "Hi, Bboyoung",
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.width(200.dp))
                                        Image(painter = painterResource(id = R.drawable.lonceng), contentDescription = "", modifier = Modifier.size(35.dp))

                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                    SearchBar {

                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                }

                            }
                        }

                    }
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Card(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(150.dp)
                                    .padding(8.dp)
                                    .shadow(10.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.grafikchartcontoh), // Ganti R.drawable.gambar_card dengan ID gambar yang Anda inginkan
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize() // Mengisi gambar sesuai ukuran Card
                                )
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
                            item {
                                Card(modifier = Modifier
                                    .width(200.dp)
                                    .height(260.dp)
                                    .padding(10.dp)
                                    .background(color = Color.White)
                                    .shadow(8.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(Color.White)) {
                                    Image(painter = painterResource(id = R.drawable.gambarorang), contentDescription = "")
                                    Column (modifier = Modifier.padding(start = 11.dp, end = 5.dp)) {
                                        Text(
                                            text = "Saham untuk Pemula",
                                            fontSize = 16.sp
                                        )
                                        Row {
                                            Text(
                                                text = "Oleh",
                                                fontSize = 12.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "Mas Ngawi",
                                                fontSize = 12.sp
                                            )
                                        }
                                        Text(
                                            text = "Lanjutkan pembelajaranmu",
                                            fontSize = 11.sp
                                        )
                                        Text(
                                            text = "dengan menonton ini",
                                            fontSize = 11.sp
                                        )
                                        Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center){
                                            Card(
                                                modifier = Modifier
                                                    .width(80.dp)
                                                    .height(40.dp),colors = CardDefaults.cardColors(Color(0xFF61008D)),shape = RoundedCornerShape(120.dp)
                                            ) {
                                                Text(
                                                    text = "Tonton",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center,
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                            item {
                                Card(modifier = Modifier
                                    .width(200.dp)
                                    .height(260.dp)
                                    .padding(10.dp)
                                    .background(color = Color.White)
                                    .shadow(8.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(Color.White)) {
                                    Image(painter = painterResource(id = R.drawable.gambarorang), contentDescription = "")
                                    Column (modifier = Modifier.padding(start = 11.dp, end = 5.dp)) {
                                        Text(
                                            text = "Saham untuk Pemula",
                                            fontSize = 16.sp
                                        )
                                        Row {
                                            Text(
                                                text = "Oleh",
                                                fontSize = 12.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "Mas Ngawi",
                                                fontSize = 12.sp
                                            )
                                        }
                                        Text(
                                            text = "Lanjutkan pembelajaranmu",
                                            fontSize = 11.sp
                                        )
                                        Text(
                                            text = "dengan menonton ini",
                                            fontSize = 11.sp
                                        )
                                        Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center){
                                            Card(
                                                modifier = Modifier
                                                    .width(80.dp)
                                                    .height(40.dp),colors = CardDefaults.cardColors(Color(0xFF61008D)),shape = RoundedCornerShape(120.dp)
                                            ) {
                                                Text(
                                                    text = "Tonton",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center,
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                            item {
                                Card(modifier = Modifier
                                    .width(200.dp)
                                    .height(260.dp)
                                    .padding(10.dp)
                                    .background(color = Color.White)
                                    .shadow(8.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(Color.White)) {
                                    Image(painter = painterResource(id = R.drawable.gambarorang), contentDescription = "")
                                    Column (modifier = Modifier.padding(start = 11.dp, end = 5.dp)) {
                                        Text(
                                            text = "Saham untuk Pemula",
                                            fontSize = 16.sp
                                        )
                                        Row {
                                            Text(
                                                text = "Oleh",
                                                fontSize = 12.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "Mas Ngawi",
                                                fontSize = 12.sp
                                            )
                                        }
                                        Text(
                                            text = "Lanjutkan pembelajaranmu",
                                            fontSize = 11.sp
                                        )
                                        Text(
                                            text = "dengan menonton ini",
                                            fontSize = 11.sp
                                        )
                                        Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), contentAlignment = Alignment.Center){
                                            Card(
                                                modifier = Modifier
                                                    .width(80.dp)
                                                    .height(40.dp),colors = CardDefaults.cardColors(Color(0xFF61008D)),shape = RoundedCornerShape(120.dp)
                                            ) {
                                                Text(
                                                    text = "Tonton",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center,
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }

                    item {
                        Box(modifier = Modifier.fillMaxWidth()){
                            Column {
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "Ikuti perkembangan Berita!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(72.dp))
                                    Card {
                                        Text(text = "Lihat Semua", modifier = Modifier
                                            .padding(3.dp)
                                            .padding(
                                                start = 2.dp,
                                                end = 2.dp,
                                                top = 2.dp,
                                                bottom = 0.dp
                                            ))
                                    }

                                }
                                Box(modifier = Modifier
                                    .width(200.dp)
                                    .height(30.dp)
                                    .padding(start = 15.dp)){
                                    Text(
                                        text = "Ikuti semua tentang investasi crypto hingga reksadana",
                                        fontSize = 10.sp
                                    )
                                }


                            }

                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, start = 13.dp)){
                            Column {
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier
                                        .fillMaxHeight()
                                        .width(200.dp)) {
                                        Text(
                                            text = "Lihat saham sambil ngopi? Siapa takut",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Minggu, 22 November 2024 - El MAngrove",
                                            fontSize = 9.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(79.dp))
                                    Box(modifier = Modifier
                                        .width(80.dp)
                                        .height(80.dp)){
                                        Image(painter = painterResource(id = R.drawable.contohgambardua) , contentDescription = "")
                                    }


                                }
                            }

                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, start = 13.dp)){
                            Column {
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier
                                        .fillMaxHeight()
                                        .width(200.dp)) {
                                        Text(
                                            text = "Lihat saham sambil ngopi? Siapa takut",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Minggu, 22 November 2024 - El Cekek",
                                            fontSize = 9.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(79.dp))
                                    Box(modifier = Modifier
                                        .width(80.dp)
                                        .height(80.dp)){
                                        Image(painter = painterResource(id = R.drawable.gambarorangtiga) , contentDescription = "")
                                    }


                                }
                            }

                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp, start = 13.dp)){
                            Column {
                                Row (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Column(modifier = Modifier
                                        .fillMaxHeight()
                                        .width(200.dp)) {
                                        Text(
                                            text = "Rekomendasi Aplikasi Trading",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Minggu, 22 November 2024 - El Cekek",
                                            fontSize = 9.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(79.dp))
                                    Box(modifier = Modifier
                                        .width(80.dp)
                                        .height(80.dp)){
                                        Image(painter = painterResource(id = R.drawable.gambargrafikdua) , contentDescription = "")
                                    }


                                }
                            }

                        }
                    }
                }
            }
        },
        topBar = {},
        bottomBar = {Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
            AppsBottomBar(currentTab = 0){
            }
        }},
    )

}

//@Composable
//@Preview
//fun DashboardAwalPreview() {
//    DashboardAwalContent()
//}
