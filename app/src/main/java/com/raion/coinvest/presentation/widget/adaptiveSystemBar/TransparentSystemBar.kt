package com.raion.coinvest.presentation.widget.adaptiveSystemBar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack

@Composable
fun AdaptiveSystemBar(){
    val systemUIController = rememberSystemUiController()
    val darkMode = isSystemInDarkTheme()
    SideEffect {
        systemUIController.setSystemBarsColor(
            color = if(darkMode){
                CoinvestBlack
            } else {
                CoinvestBase
                   },
            darkIcons = if(darkMode){
                false
            } else {
                true
            }
        )
    }
}