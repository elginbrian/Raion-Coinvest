package com.raion.coinvest.presentation.widget.searchBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField

@Composable
//@Preview
fun SearchBar(
  onClick: () -> Unit
){
    val isClicked = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
            CoinvestBlack
        } else {
            CoinvestLightGrey
        }),
        border = BorderStroke(1.dp, CoinvestBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .fillMaxSize(0.8f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isClicked.value = true
                    onClick()
                }) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    if(isClicked.value){
                        Spacer(modifier = Modifier.padding(1.dp))
                        TransparentTextField(onValueChange = {}, onFocusChange = {})
                    } else {
                        Text(text = "Cari", color =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }
                }
            }
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search",
                modifier = Modifier.size(32.dp),
                tint =  if(isSystemInDarkTheme()){
                    CoinvestBase
                } else {
                    CoinvestBlack
                }
            )
        }
    }
}