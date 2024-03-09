package com.raion.coinvest.presentation.appsBottomBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestBlack

@Composable
@Preview
fun AppsBottomBar(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(4.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(CoinvestBase),
        border = BorderStroke(2.dp, CoinvestDarkPurple)
        ) {
        val tabIndex = remember {
            mutableStateOf(0)
        }
        TabRow(
            modifier = Modifier.fillMaxSize(),
            selectedTabIndex = tabIndex.value,
            containerColor = Color.Transparent,
            contentColor = CoinvestBlack,
            divider = {},
            indicator = {}
        ){
            Tab(selected = tabIndex.value == 0, onClick = { tabIndex.value = 0 }) {
                Icon(painter = painterResource(id = R.drawable.home_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f))
            }
            Tab(selected = tabIndex.value == 1, onClick = { tabIndex.value = 1 }) {
                Icon(painter = painterResource(id = R.drawable.course_icon), contentDescription = "Course", modifier = Modifier.scale(0.65f))
            }
            Tab(selected = tabIndex.value == 2, onClick = { tabIndex.value = 2 }) {
                Icon(painter = painterResource(id = R.drawable.stats_icon), contentDescription = "Stats", modifier = Modifier.scale(0.65f))
            }
            Tab(selected = tabIndex.value == 3, onClick = { tabIndex.value = 3 }) {
                Icon(painter = painterResource(id = R.drawable.forum_icon), contentDescription = "Forum", modifier = Modifier.scale(0.65f))
            }
            Tab(selected = tabIndex.value == 4, onClick = { tabIndex.value = 4 }) {
                Icon(painter = painterResource(id = R.drawable.article_icon), contentDescription = "Article",modifier = Modifier.scale(0.65f))
            }
        }
    }
}