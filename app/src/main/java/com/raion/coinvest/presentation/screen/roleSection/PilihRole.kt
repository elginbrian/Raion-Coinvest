package com.raion.coinvest.presentation.screen.roleSection

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
import androidx.compose.foundation.layout.width
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

@Composable
@Preview
fun MenuDaftar() {
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
                        text = "Pilih Role Kamu",
                        color = Color.White,
                        fontSize = 14.sp, modifier = Modifier.padding(start = 125.dp)
                    )
                }
                Spacer(modifier = Modifier.height(90.dp))
                Card (modifier = Modifier
                    .width(317.dp)
                    .height(150.dp), colors = CardDefaults.cardColors(CoinvestBorderDepan)) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(15.dp))
                            Box(modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.kotakpertama),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .width(200.dp)
                                .padding(top = 40.dp, start = 25.dp, end = 15.dp)
                            ) {
                                Text(
                                    text = "Author",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold, color = Color.White
                                )
                                Text(
                                    text = "Tulis berita dan Anda menjadi sebuah penulis berita mengenai investasi dan trading",
                                    fontSize = 9.sp, color = Color.White
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Card (modifier = Modifier
                    .width(317.dp)
                    .height(150.dp), colors = CardDefaults.cardColors(CoinvestBorderDepan)) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                            ,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(15.dp))
                            Box(modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.memberpertama),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .width(200.dp)
                                .padding(top = 40.dp, start = 25.dp, end = 15.dp)
                            ) {
                                Text(
                                    text = "Member",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold, color = Color.White
                                )
                                Text(
                                    text = "Anda menjadi investor yang siap belajar dan saling memberi informasi mengenai investasi dan trading",
                                    fontSize = 9.sp, color = Color.White
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Card (modifier = Modifier
                    .width(317.dp)
                    .height(150.dp), colors = CardDefaults.cardColors(CoinvestBorderDepan)) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                            ,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(15.dp))
                            Box(modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mentorkotak),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .width(200.dp)
                                .padding(top = 40.dp, start = 25.dp, end = 15.dp)
                            ) {
                                Text(
                                    text = "Mentor",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold, color = Color.White
                                )
                                Text(
                                    text = "Anda menjadi sebuah mentor dan pengajar course mengenai investasi dan trading",
                                    fontSize = 9.sp, color = Color.White
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(110.dp))
                Card (modifier = Modifier
                    .width(266.dp)
                    .height(62.dp)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(text = "Selanjutnya", fontSize = 16.sp, color = CoinvestDarkPurple)
                    }


                }
            }
        }
    }
}