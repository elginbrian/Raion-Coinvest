package com.raion.coinvest.presentation.screen.roleSection.memberVerifSection


import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firebaseStorage.model.VerifDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun MemberVerivRole(
    onFinished: (Pair<VerifDataClass, UserDataClass>) -> Unit,
    onTapBack: () -> Unit
){
    val username = remember { mutableStateOf(Firebase.auth.currentUser?.email?.substringBefore('@').toString()) }
    val userId   = remember { mutableStateOf(Firebase.auth.currentUser?.uid.toString()) }

    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )

    val selectedImageUri2 = remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher2 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri2.value = uri }
    )

    Scaffold(
        containerColor = CoinvestGrey,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", modifier = Modifier.clickable {
                        onTapBack()
                    })
                    
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(modifier = Modifier
                        .width(66.dp)
                        .height(26.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            val verif = VerifDataClass(
                                userId = userId.value,
                                accountType = "member",
                                document1 = Uri.EMPTY,
                                document2 = Uri.EMPTY
                            )
                            val user = UserDataClass(
                                userId = userId.value,
                                userName = username.value,
                                accountType = "member",
                                email = Firebase.auth.currentUser?.email,
                                profilePicture = selectedImageUri.value.toString()
                            )
                            onFinished(Pair(verif, user))
                        },
                        colors = CardDefaults.cardColors(CoinvestPurple),
                        shape = RoundedCornerShape(50.dp)
                    ){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(
                                text = "Simpan", fontSize = 12.sp,
                                color = CoinvestBase
                            )
                        }

                    }
                }
            }
        },
        content = {

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .clickable {
                    singlePhotoPickerLauncher2.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    ,contentAlignment = Alignment.Center){
                    AsyncImage(model = selectedImageUri2.value, contentDescription = "profile picture", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Image(painter = painterResource(id = R.drawable.tandatambahrole), contentDescription = "", modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.padding(40.dp))
                    }
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.85f),
                contentAlignment = Alignment.TopCenter
            ){
                Column(modifier = Modifier) {
                    Spacer(modifier = Modifier.padding(32.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, end = 30.dp, top = 60.dp)) {
                            Text(text = "Username", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp, bottom = 4.dp), color =  if(isSystemInDarkTheme()){
                                CoinvestBase
                            } else {
                                CoinvestBlack
                            })
                            Card(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(50.dp),

                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, CoinvestBorder),
                                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                    MaterialTheme.colorScheme.background
                                } else {
                                    CoinvestBase
                                })
                            ) {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp), verticalArrangement = Arrangement.Center) {
                                    Spacer(modifier = Modifier.padding(1.dp))
                                    TransparentTextField(
                                        text = username.value,
                                        onValueChange = {
                                            if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                                username.value = it
                                            }
                                        }, onFocusChange = {},
                                        fontSize = 28.sp,
                                        singleLine = true
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "UserID", fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 15.dp, bottom = 4.dp), color =  if(isSystemInDarkTheme()){
                                CoinvestBase
                            } else {
                                CoinvestBlack
                            })
                            Card(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(50.dp),

                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, CoinvestBorder),
                                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                    MaterialTheme.colorScheme.background
                                } else {
                                    CoinvestBase
                                })
                            ) {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp), verticalArrangement = Arrangement.Center) {
                                    Spacer(modifier = Modifier.padding(1.dp))
                                    TransparentTextField(
                                        text = userId.value,
                                        onValueChange = {
//                                            if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
//                                                userId.value = it
//                                            }
                                        }, onFocusChange = {},
                                        fontSize = 28.sp,
                                        singleLine = true
                                    )
                                }
                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .padding(end = 15.dp), contentAlignment = Alignment.CenterEnd){
                                Text(text = "UserId tidak dapat diubah", fontSize = 11.sp, color =  if(isSystemInDarkTheme()){
                                    CoinvestBase
                                } else {
                                    CoinvestBlack
                                })
                            }
//                            Spacer(modifier = Modifier.height(40.dp))
//                            Card(
//                                modifier = Modifier
//                                    .width(320.dp)
//                                    .height(50.dp),
//
//                                shape = RoundedCornerShape(8.dp),
//                                border = BorderStroke(1.dp, CoinvestBorder),
//                                colors = CardDefaults.cardColors(Color.White)
//                            ) {
//
//                            }
//                            Box(modifier = Modifier
//                                .fillMaxWidth()
//                                .height(30.dp)
//                                .padding(end = 15.dp), contentAlignment = Alignment.CenterEnd){
//                                Text(text = "200", fontSize = 11.sp)
//                            }
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                            CoinvestBlack
                        } else {
                            CoinvestLightGrey
                        })
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                            , contentAlignment = Alignment.Center) {
                            AsyncImage(model = selectedImageUri.value, contentDescription = "profile picture", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            Image(
                                painter = painterResource(id = R.drawable.ditambahtambah),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(0.5f)
                            )
                        }
                    }
                }

            }

        }
    )
}
