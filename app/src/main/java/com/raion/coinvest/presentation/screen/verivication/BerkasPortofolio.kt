package com.raion.coinvest.presentation.screen.verivication


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBorderDepan
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestPurple

@Composable
@Preview
fun BerkasPortofolio() {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Image(
            painter = painterResource(id = R.drawable.backgroundgradient),
            contentDescription = "Deskripsi Gambar",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .width(200.dp)
                .height(200.dp)
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 30.dp, start = 15.dp, end = 30.dp)) {
                    Image(painter = painterResource(id = R.drawable.tombolbalik), contentDescription = "")
                    Text(
                        text = "Verifikasi Berkas",
                        color = Color.White,
                        fontSize = 14.sp, modifier = Modifier.padding(start = 125.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Box(
                        modifier = Modifier
                            .size(35.dp) // Atur ukuran lingkaran di sini
                            .background(CoinvestPurple, shape = CircleShape), // Warna dan bentuk lingkaran
                        contentAlignment = Alignment.Center // Posisikan angka di tengah lingkaran
                    ) {
                        Text(
                            text = "1",
                            color = Color.White, // Warna teks
                            fontSize = 13.sp // Ukuran teks
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(20.dp) // Atur ukuran lingkaran di sini
                            .background(CoinvestPurple, shape = CircleShape), // Warna dan bentuk lingkaran
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(35.dp) // Atur ukuran lingkaran di sini
                            .background(CoinvestDarkPurple, shape = CircleShape), // Warna dan bentuk lingkaran
                        contentAlignment = Alignment.Center // Posisikan angka di tengah lingkaran
                    ) {
                        Text(
                            text = "2",
                            color = Color.White, // Warna teks
                            fontSize = 13.sp // Ukuran teks
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(20.dp) // Atur ukuran lingkaran di sini
                            .background(CoinvestPurple, shape = CircleShape), // Warna dan bentuk lingkaran
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(35.dp) // Atur ukuran lingkaran di sini
                            .background(CoinvestPurple, shape = CircleShape), // Warna dan bentuk lingkaran
                        contentAlignment = Alignment.Center // Posisikan angka di tengah lingkaran
                    ) {
                        Text(
                            text = "3",
                            color = Color.White, // Warna teks
                            fontSize = 13.sp // Ukuran teks
                        )
                    }
                }
                Spacer(modifier = Modifier.height(39.dp))
                Text(text = "Portofolio", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(15.dp))
                Column {
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et massa mi. ", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et massa mi. ", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et massa mi. ", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et massa mi. ", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Card (modifier = Modifier
                    .height(160.dp)
                    .width(320.dp),colors = CardDefaults.cardColors(Color.White)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.tandatambah),
                                contentDescription = "Tanda Tambah", modifier = Modifier.size(50.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Card (modifier = Modifier
                                .width(160.dp)
                                .height(40.dp),border = BorderStroke(1.dp, CoinvestDarkPurple),colors = CardDefaults.cardColors(Color.White)) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, ){
                                    Text(text = "Tambah File", fontSize = 14.sp, color = CoinvestDarkPurple)
                                }


                            }

                        }

                    }
                }
                Spacer(modifier = Modifier.height(196.dp))
                Card (modifier = Modifier
                    .width(266.dp)
                    .height(62.dp),colors = CardDefaults.cardColors(Color.White)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(text = "Simpan dan lanjutkan", fontSize = 16.sp, color = CoinvestDarkPurple)
                    }


                }
            }
        }
    }
}