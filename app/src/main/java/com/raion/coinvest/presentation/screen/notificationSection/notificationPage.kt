package com.raion.coinvest.presentation.screen.notificationSection


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestCardNotif
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
@Preview
fun NotificationPage() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(modifier = Modifier.fillMaxSize()){

            item {
                Card (modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)) {
                    Box(modifier = Modifier
                        .fillMaxSize(), contentAlignment = Alignment.CenterStart){
                        Column (modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Row  {
                                Spacer(modifier = Modifier.width(15.dp))
                                Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint = Color.Black)
                                Spacer(modifier = Modifier.width(130.dp))
                                Text(text = "Notifikasi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(110.dp))
                                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search button", tint = Color.Black, modifier = Modifier.size(28.dp) )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Row {
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Semua", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Diikuti", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Diarsip", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                    }
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Hari Ini", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(190.dp))
                            Card (colors = CardDefaults.cardColors(CoinvestCardNotif)){
                                Text(text = "Tandai Telah Dibaca")
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Card(
                            modifier = Modifier
                                .width(328.dp)
                                .height(100.dp),
                            colors = CardDefaults.cardColors(CoinvestCardNotif)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp) // Padding tambahan jika diperlukan
                                    .fillMaxSize(), // Mengisi ukuran maksimum di dalam Column
                                verticalArrangement = Arrangement.Center // Menengahkan objek secara vertikal
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(shape = CircleShape),
                                        colors = CardDefaults.cardColors(Color.White),
                                    ) {
                                        // Konten dalam Card pertama
                                    }
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "MIcah 88")
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "22j")
                                    Spacer(modifier = Modifier.width(150.dp)) // Spacer untuk memberikan jarak antar objek
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Sampah",
                                        tint = CoinvestDarkPurple, // Warna ikon
                                        modifier = Modifier.size(24.dp) // Ukuran ikon
                                    )
                                }
                                Text(text = "Menyukai Postingan Anda", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Card(
                            modifier = Modifier
                                .width(328.dp)
                                .height(270.dp),
                            colors = CardDefaults.cardColors(CoinvestCardNotif)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp) // Padding tambahan jika diperlukan
                                    .fillMaxSize(), // Mengisi ukuran maksimum di dalam Column
                                verticalArrangement = Arrangement.Center // Menengahkan objek secara vertikal
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(shape = CircleShape),
                                        colors = CardDefaults.cardColors(Color.White),
                                    ) {
                                        // Konten dalam Card pertama
                                    }
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "MIcah 88")
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "22j")
                                    Spacer(modifier = Modifier.width(150.dp)) // Spacer untuk memberikan jarak antar objek
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Sampah",
                                        tint = CoinvestDarkPurple, // Warna ikon
                                        modifier = Modifier.size(24.dp) // Ukuran ikon
                                    )
                                }
                                Text(text = "Menyukai Postingan Anda", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
                                Image(painter = painterResource(id = R.drawable.framenotif), contentDescription = "", modifier = Modifier.size(300.dp))
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.fillMaxSize().padding(top = 15.dp, start = 15.dp, end = 15.dp)) {
                    Text(text = "Hari Ini", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
            item {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp), contentAlignment = Alignment.Center){
                    Column {
                        Card(
                            modifier = Modifier
                                .width(328.dp)
                                .height(100.dp),
                            colors = CardDefaults.cardColors(CoinvestLightGrey)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp) // Padding tambahan jika diperlukan
                                    .fillMaxSize(), // Mengisi ukuran maksimum di dalam Column
                                verticalArrangement = Arrangement.Center // Menengahkan objek secara vertikal
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(shape = CircleShape),
                                        colors = CardDefaults.cardColors(Color.White),
                                    ) {
                                        // Konten dalam Card pertama
                                    }
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "MIcah 88")
                                    Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                    Text(text = "22j")
                                    Spacer(modifier = Modifier.width(150.dp)) // Spacer untuk memberikan jarak antar objek
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Sampah",
                                        tint = CoinvestDarkPurple, // Warna ikon
                                        modifier = Modifier.size(24.dp) // Ukuran ikon
                                    )
                                }
                                Text(
                                    text = "Menyukai Postingan Anda",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp), contentAlignment = Alignment.Center){

                    Card(
                        modifier = Modifier
                            .width(328.dp)
                            .height(270.dp),
                        colors = CardDefaults.cardColors(CoinvestLightGrey)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp) // Padding tambahan jika diperlukan
                                .fillMaxSize(), // Mengisi ukuran maksimum di dalam Column
                            verticalArrangement = Arrangement.Center // Menengahkan objek secara vertikal
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(shape = CircleShape),
                                    colors = CardDefaults.cardColors(Color.White),
                                ) {
                                    // Konten dalam Card pertama
                                }
                                Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                Text(text = "MIcah 88")
                                Spacer(modifier = Modifier.width(8.dp)) // Spacer untuk memberikan jarak antar objek
                                Text(text = "22j")
                                Spacer(modifier = Modifier.width(150.dp)) // Spacer untuk memberikan jarak antar objek
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Sampah",
                                    tint = CoinvestDarkPurple, // Warna ikon
                                    modifier = Modifier.size(24.dp) // Ukuran ikon
                                )
                            }
                            Text(text = "Menyukai Postingan Anda", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
                            Image(painter = painterResource(id = R.drawable.framenotif), contentDescription = "", modifier = Modifier.size(300.dp))
                        }
                    }
                }
            }
        }
    }
}