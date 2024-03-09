package com.raion.coinvest.presentation.loginSection

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.media3.extractor.text.webvtt.WebvttCssStyle.FontSizeUnit
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.auth.EmailAuthRepository
import com.raion.coinvest.data.remote.auth.model.SignInResult
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.transparentTextField.TransparentTextField

@Composable
fun LoginHome(
    viewModel: LoginViewModel,
    onSignInWithEmail: (Pair<String, String>) -> SignInResult,
    onSignInWithGoogle: () -> Unit,
    onChangeScreen: () -> Unit
) {
    val signInResult = remember { mutableStateOf<SignInResult?>(null) }
    val context = LocalContext.current
    val checked = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = state.isSignInSuccessful){
        if(state.isSignInSuccessful){
            Toast.makeText(context, "Sign in success", Toast.LENGTH_LONG).show()
            onChangeScreen()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = CoinvestBase),
        contentAlignment = Alignment.Center

    ){
        Image(painter = painterResource(id = R.drawable.background_purple), contentDescription = "background", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Spacer(modifier = Modifier.height(50.dp))
            Image(painter = painterResource(id = R.drawable.logocoinvest), contentDescription = "")
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Hi There!", fontSize = 14.sp, color = CoinvestBase)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Let’s Get Started", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = CoinvestBase)
            Spacer(modifier = Modifier.height(50.dp))
            Card(modifier = Modifier
                .width(304.dp)
                .height(55.dp),
                shape = RoundedCornerShape(16.dp),
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

                    Image(painter = painterResource(id = R.drawable.mail),
                        contentDescription = "",
                        modifier = Modifier
                            .width(21.dp)
                            .height(21.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    TransparentTextField(
                        text = email.value,
                        onValueChange = {
                            if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                email.value = it
                            }
                        }, onFocusChange = {},
                        fontSize = 28.sp,
                        singleLine = true
                    )

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
                    Spacer(modifier = Modifier.width(16.dp))
                    TransparentTextField(
                        text = password.value,
                        onValueChange = {
                            if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                password.value = it
                            }
                        }, onFocusChange = {},
                        fontSize = 28.sp,
                        singleLine = true
                    )
                }
            }
            Row(modifier = Modifier.width(321.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Switch(
                        checked = checked.value,
                        onCheckedChange = {
                            checked.value = !checked.value
                        },
                        modifier = Modifier.scale(0.7f),

                        )
                    Text(text = "Remember Me", fontSize = 11.sp, color = CoinvestBase)
                }
                Row() {
                    Text(text = "Forgot Password?", fontSize = 11.sp, color = CoinvestBase)
                    Spacer(modifier = Modifier.padding(6.dp))
                }

            }
            Spacer(modifier = Modifier.height(90.dp))
            Image(painter = painterResource(id = R.drawable.loginbaru),
                contentDescription = "",
                modifier = Modifier
                    .width(265.dp)
                    .height(55.dp)
                    .clickable {
                        onSignInWithEmail(Pair(email.value, password.value))
                        email.value = ""
                        password.value = ""
                        onChangeScreen()
//                        signInResult.value = signInResult.value?.apply {
//                            val result   = onSignInWithEmail(Pair(email.value, password.value))
//                            isSuccess    = result.isSuccess
//                            data         = result.data
//                            errorMessage = result.errorMessage
//                        }

//                        if (signInResult.value?.isSuccess == true) {
//                            Toast.makeText(context, "Sign in success", Toast.LENGTH_LONG).show()
//                            onChangeScreen()
//                        } else {
//                            Toast.makeText(context, signInResult.value?.errorMessage.toString(), Toast.LENGTH_LONG).show()
//                            Log.d("signInResult", signInResult.toString())
//                        }
                    },

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "OR", fontSize = 13.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Image(painter = painterResource(id = R.drawable.daftargooglenoshad),
                contentDescription = "",
                modifier = Modifier
                    .width(265.dp)
                    .height(55.dp)
                    .clickable {
                        onSignInWithGoogle()
                    }
                )
            }
            Spacer(modifier = Modifier.height(80.dp))

        }
    }

@Preview
@Composable
fun LoginScreenPreview(){
    LoginHome(viewModel = LoginViewModel(emailAuthRepository = EmailAuthRepository()),
        onSignInWithEmail = { SignInResult(data = null, errorMessage = null) }, onSignInWithGoogle = {}, onChangeScreen = {})
}


