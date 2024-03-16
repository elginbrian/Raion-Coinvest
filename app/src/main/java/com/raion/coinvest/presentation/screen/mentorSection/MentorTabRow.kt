package com.raion.coinvest.presentation.screen.mentorSection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.presentation.widget.appsBottomBar.DisabledInteractionSource

@Composable
@Preview
fun MentorTabRow(){
    var tabIndex = remember {
        mutableStateOf(0)
    }
    TabRow(
        modifier = Modifier.heightIn(32.dp),
        selectedTabIndex = tabIndex.value,
        containerColor = Color.Transparent,
        contentColor = CoinvestPurple,
        divider = {},
        indicator = {}
    ) {
        Tab(
            selected = tabIndex.value == 0,
            onClick = { tabIndex.value = 0 },
            interactionSource = DisabledInteractionSource()
        ) {
            Card(modifier = Modifier
                .width(54.dp)
                .height(20.dp),
                colors = if(tabIndex.value == 0){
                    CardDefaults.cardColors(CoinvestDarkPurple)
                } else {
                    CardDefaults.cardColors(CoinvestLightGrey)
                }
            ){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "Semua", fontSize = 10.sp,
                        color = if(tabIndex.value == 0){
                            CoinvestBase
                        } else {
                            CoinvestDarkPurple
                        }
                    )
                }

            }
        }

        Tab(
            selected = tabIndex.value == 1,
            onClick = { tabIndex.value = 1 },
            interactionSource = DisabledInteractionSource()
            ) {
            Card(modifier = Modifier
                .width(54.dp)
                .height(20.dp),
                colors = if(tabIndex.value == 1){
                    CardDefaults.cardColors(CoinvestDarkPurple)
                } else {
                    CardDefaults.cardColors(CoinvestLightGrey)
                }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "Bitcoin", fontSize = 10.sp,
                        color = if(tabIndex.value == 1){
                            CoinvestBase
                        } else {
                            CoinvestDarkPurple
                        }
                    )
                }

            }
        }
        Tab(
            selected = tabIndex.value == 2,
            onClick = { tabIndex.value = 2 },
            interactionSource = DisabledInteractionSource()
        ) {
            Card(modifier = Modifier
                .width(54.dp)
                .height(20.dp),
                colors = if(tabIndex.value == 2){
                    CardDefaults.cardColors(CoinvestDarkPurple)
                } else {
                    CardDefaults.cardColors(CoinvestLightGrey)
                }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "Etherium", fontSize = 10.sp,
                        color = if(tabIndex.value == 2){
                            CoinvestBase
                        } else {
                            CoinvestDarkPurple
                        }
                    )
                }

            }
        }
        Tab(
            selected = tabIndex.value == 3,
            onClick = { tabIndex.value = 3 },
            interactionSource = DisabledInteractionSource()
        ) {
            Card(modifier = Modifier
                .width(54.dp)
                .height(20.dp),
                colors = if(tabIndex.value == 3){
                    CardDefaults.cardColors(CoinvestDarkPurple)
                } else {
                    CardDefaults.cardColors(CoinvestLightGrey)
                }
            ){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "Tether", fontSize = 10.sp,
                        color = if(tabIndex.value == 3){
                            CoinvestBase
                        } else {
                            CoinvestDarkPurple
                        }
                    )
                }

            }
        }
    }
}