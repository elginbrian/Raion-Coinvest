package com.raion.coinvest.presentation.widget.transparentTextField

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack

@Composable
fun TransparentTextField(
    text: String = "",
    hint: String = "",
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle : androidx.compose.ui.text.TextStyle = LocalTextStyle.current.copy(color = if(isSystemInDarkTheme()){
        CoinvestBase
    } else {
        CoinvestBlack
    }),
    singleLine: Boolean = false,
    fontSize: TextUnit = 20.sp,
    onFocusChange: (Any) -> Unit
){
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged { onFocusChange(it) }
        )
        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray, fontSize = fontSize)
        }
    }
}