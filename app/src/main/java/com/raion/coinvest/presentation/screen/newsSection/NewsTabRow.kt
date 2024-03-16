package com.raion.coinvest.presentation.screen.newsSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.widget.appsBottomBar.DisabledInteractionSource

@Composable
fun NewsTabRow(
    onClick: (Int) -> Unit
){
    val tabIndex = remember {
        mutableStateOf(0)
    }
    onClick(tabIndex.value)

    TabRow(
        modifier = Modifier.heightIn(32.dp),
        selectedTabIndex = tabIndex.value,
        containerColor = Color.Transparent,
        contentColor = CoinvestBlack
    ){
        Tab(
            selected = tabIndex.value == 0,
            onClick = {
                tabIndex.value = 0
            },
            interactionSource = DisabledInteractionSource()
        ){
            Column {
                Text(text = "Beranda", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
            }

        }
        Tab(
            selected = tabIndex.value == 1,
            onClick = { tabIndex.value = 1 },
            interactionSource = DisabledInteractionSource()
        ){
            Column {
                Text(text = "Tersimpan", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}