package com.raion.coinvest.presentation.widget.transparentSystemBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBar(){
    val systemUIController = rememberSystemUiController()

    SideEffect {
        systemUIController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
}