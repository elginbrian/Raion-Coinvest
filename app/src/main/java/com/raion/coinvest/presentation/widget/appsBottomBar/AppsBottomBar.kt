package com.raion.coinvest.presentation.widget.appsBottomBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun AppsBottomBar(
    currentTab: Int = 0,
    onChangeTab: (Int) -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(62.dp)
        .padding(vertical = 4.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
            CoinvestBlack
        } else {
            CoinvestLightGrey
        }),
        elevation = CardDefaults.cardElevation(12.dp)
        ) {
        val tabIndex = remember {
            mutableStateOf(currentTab)
        }

        TabRow(
            modifier = Modifier.fillMaxSize(),
            selectedTabIndex = tabIndex.value,
            containerColor = Color.Transparent,
            contentColor = CoinvestBlack,
            divider = {},
            indicator = {}
        ){
            Tab(selected = tabIndex.value == 0,
                interactionSource = DisabledInteractionSource()
                , onClick = {
                tabIndex.value = 0
                onChangeTab(tabIndex.value)
            }) {
                if(tabIndex.value == 0){
                    Card(shape = CircleShape, colors = CardDefaults.cardColors(CoinvestDarkPurple), modifier = Modifier.size(42.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Icon(painter = painterResource(id = R.drawable.home_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint = CoinvestBase)
                        }
                    }
                } else {
                    Icon(painter = painterResource(id = R.drawable.home_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }
            }

            Tab(selected = tabIndex.value == 1,
                interactionSource = DisabledInteractionSource()
                , onClick = {
                tabIndex.value = 1
                onChangeTab(tabIndex.value)
            }) {
                if(tabIndex.value == 1){
                    Card(shape = CircleShape, colors = CardDefaults.cardColors(CoinvestDarkPurple), modifier = Modifier.size(42.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Icon(painter = painterResource(id = R.drawable.course_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint = CoinvestBase)
                        }
                    }
                } else {
                    Icon(painter = painterResource(id = R.drawable.course_icon), contentDescription = "Course", modifier = Modifier.scale(0.65f), if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }

            }

            Tab(selected = tabIndex.value == 2,
                interactionSource = DisabledInteractionSource()
                , onClick = {
                tabIndex.value = 2
                onChangeTab(tabIndex.value)
            }) {
                if(tabIndex.value == 2){
                    Card(shape = CircleShape, colors = CardDefaults.cardColors(CoinvestDarkPurple), modifier = Modifier.size(42.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Icon(painter = painterResource(id = R.drawable.stats_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint = CoinvestBase)
                        }
                    }
                } else {
                    Icon(painter = painterResource(id = R.drawable.stats_icon), contentDescription = "Stats", modifier = Modifier.scale(0.65f), if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }
            }

            Tab(selected = tabIndex.value == 3,
                interactionSource = DisabledInteractionSource()
                , onClick = {
                tabIndex.value = 3
                onChangeTab(tabIndex.value)
            }) {
                if(tabIndex.value == 3){
                    Card(shape = CircleShape, colors = CardDefaults.cardColors(CoinvestDarkPurple), modifier = Modifier.size(42.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Icon(painter = painterResource(id = R.drawable.forum_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint = CoinvestBase)
                        }
                    }
                } else {
                    Icon(painter = painterResource(id = R.drawable.forum_icon), contentDescription = "Forum", modifier = Modifier.scale(0.65f), if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }
            }

            Tab(selected = tabIndex.value == 4,
                interactionSource = DisabledInteractionSource(),
                onClick = {
                tabIndex.value = 4
                onChangeTab(tabIndex.value)
            }) {
                if(tabIndex.value == 4){
                    Card(shape = CircleShape, colors = CardDefaults.cardColors(CoinvestDarkPurple), modifier = Modifier.size(42.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Icon(painter = painterResource(id = R.drawable.article_icon), contentDescription = "Home", modifier = Modifier.scale(0.65f), tint = CoinvestBase)
                        }
                    }
                } else {
                    Icon(painter = painterResource(id = R.drawable.article_icon), contentDescription = "Article",modifier = Modifier.scale(0.65f), if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }
            }
        }
    }
}

class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}