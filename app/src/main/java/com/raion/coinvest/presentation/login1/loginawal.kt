package com.raion.coinvest.presentation.login1

import android.widget.Switch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.R
import com.raion.coinvest.presentation.designSystem.CoinvestBlue
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import org.w3c.dom.Text
import java.time.format.TextStyle

@Composable
@Preview
fun LoginHome() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        contentAlignment = Alignment.Center

    ){
        var checked = remember { 
            mutableStateOf(false) 
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Spacer(modifier = Modifier.height(90.dp))
            Image(painter = painterResource(id = R.drawable.logocoinvest), contentDescription = "")
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Hi There!", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Let’s Get Started", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(50.dp))
            Card(modifier = Modifier
                .width(304.dp)
                .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(CoinvestLightGrey),
                border = BorderStroke(2.dp, CoinvestBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Spacer(modifier = Modifier.height(30.dp))
                    Image(painter = painterResource(id = R.drawable.mail),
                        contentDescription = "",
                        modifier = Modifier
                            .width(21.dp)
                            .height(21.dp))
                    Spacer(modifier = Modifier.width(25.dp))
                    TransparentTextField(onValueChange = {}, onFocusChange = {})

                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Card(modifier = Modifier
                .width(304.dp)
                .height(55.dp),
                shape = RoundedCornerShape(16.dp),
//                elevation = CardDefaults
                colors = CardDefaults.cardColors(CoinvestLightGrey),
                border = BorderStroke(2.dp, CoinvestBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.password),
                        contentDescription = "",
                        modifier = Modifier
                            .width(21.dp)
                            .height(21.dp))
                    Spacer(modifier = Modifier.width(25.dp))
                    TransparentTextField(onValueChange = {}, onFocusChange = {})
                }
            }
            Row(modifier = Modifier.width(321.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = !checked.value
                    },
                    modifier = Modifier.scale(0.7f),

                )
                Text(text = "Remember Me", fontSize = 11.sp)
                Spacer(modifier = Modifier.width(102.dp))
                Text(text = "Forgot Password?", fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.height(110.dp))
            Image(painter = painterResource(id = R.drawable.loginbaru),
                contentDescription = "",
//                modifier = Modifier.size(280.dp)
                modifier = Modifier
                    .width(265.dp)
                    .height(55.dp),

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "OR", fontSize = 13.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Image(painter = painterResource(id = R.drawable.daftargooglenoshad),
                contentDescription = "",
                modifier = Modifier
                    .width(265.dp)
                    .height(55.dp)
                )
//            Card(modifier = Modifier
//                .width(265.dp)
//                .height(55.dp),
//                shape = RoundedCornerShape(16.dp)
//            ){
//                Row(modifier = Modifier
//                    .fillMaxSize(),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    TransparentTextField(onValueChange = {}, onFocusChange = {})
////                    TransparentTextField(onValueChange = {}, textStyle = {}, onFocusChange = )
////                    Image(painter = painterResource(id = R.drawable.google),
////                        contentDescription ="",
////                        modifier = Modifier.width(30.dp). height(30.dp))
////                    Spacer(modifier = Modifier.width(30.dp))
////                    Text(text = "Google")
//
//                }
//            }

            }
        }
    }



@Composable
fun TransparentTextField(
    text: String = "",
    hint: String = "",
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
//    textStyle: TextStyle = TextStyle(),
    textStyle : androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (Any) -> Unit
){
    Box(
        modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
//                .fillMaxSize()
                .onFocusChanged { onFocusChange(it) }
        )
        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}


