package com.raion.coinvest.presentation.screen.verivicationMember


import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostCard
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.screen.communityProfileSection.CommunityProfileTabRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MemberVerivRole(
//    onChangeTab: (Int) -> Unit
){
    Scaffold(
        containerColor = CoinvestGrey,
        content = {

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    ,contentAlignment = Alignment.CenterStart){
                    Row {
                        Image(painter = painterResource(id = R.drawable.tombolbalik), contentDescription = "", modifier = Modifier.padding(start = 15.dp))
                        Spacer(modifier = Modifier.width(280.dp))
                        Card(modifier = Modifier
                            .width(65.dp)
                            .height(24.dp),colors = CardDefaults.cardColors(CoinvestDarkPurple)) {
                            Text(
                                text = "Simpan",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize(), color = Color.White
                            )
                        }
                    }

                }
                Image(painter = painterResource(id = R.drawable.tandatambahrole), contentDescription = "")
                Spacer(modifier = Modifier.height(125.dp))
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.85f),
                contentAlignment = Alignment.TopCenter
            ){
                Column(modifier = Modifier) {
                    Spacer(modifier = Modifier.padding(32.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), colors = CardDefaults.cardColors(
                            Color.White)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, end = 30.dp, top = 60.dp)) {
                            Text(text = "Ussername", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp, bottom = 4.dp))
                            Card(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(50.dp),

                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, CoinvestBorder),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {

                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "UserID", fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 15.dp, bottom = 4.dp))
                            Card(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(50.dp),

                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, CoinvestBorder),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                            Card(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(50.dp),

                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, CoinvestBorder),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {

                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .padding(end = 15.dp), contentAlignment = Alignment.CenterEnd){
                                Text(text = "200", fontSize = 11.sp)
                            }
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(CoinvestLightGrey)
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.ditambahtambah),
                                contentDescription = ""
                            )
                        }
                    }
                }

            }

        }
    )
}
