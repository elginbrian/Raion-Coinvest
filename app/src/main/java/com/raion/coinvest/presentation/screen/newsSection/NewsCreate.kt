package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.presentation.screen.mentorSection.CourseCard
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField
import com.raion.coinvest.presentation.widget.videoPlayerCard.VideoPlayerCard
import java.time.LocalDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun NewsCreate(
    onCreateNews: (NewsDataClass) -> Unit,
    onTapBack: () -> Unit
){

    val header           = remember { mutableStateOf("") }
    val content          = remember { mutableStateOf("") }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
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
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = "Buat Berita", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(modifier = Modifier
                        .width(66.dp)
                        .height(26.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onCreateNews( NewsDataClass(
                                newsId = UUID.randomUUID().toString(),
                                newsTitle = header.value,
                                newsCategory = "",
                                newsAuthor = UserDataClass(
                                    userId = Firebase.auth.currentUser?.uid,
                                    userName = Firebase.auth.currentUser?.displayName,
                                    profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                    accountType = "author",
                                    email = Firebase.auth.currentUser?.email
                                ),
                                newsCreatedAt = LocalDateTime.now().toString(),
                                newsContent = content.value,
                                imageUri = selectedImageUri.value ?: Uri.EMPTY,
                            )
                            )
                        },
                        colors = CardDefaults.cardColors(CoinvestPurple),
                        shape = RoundedCornerShape(50.dp)
                    ){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(
                                text = "Publish", fontSize = 12.sp,
                                color = CoinvestBase
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
                }
            }
        },
        content = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.28f),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(model = selectedImageUri.value, contentDescription = "news picture", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                Card(modifier = Modifier.size(44.dp).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = CircleShape
                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add image", tint = CoinvestBase)
                    }
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.82f),
                contentAlignment = Alignment.TopStart
            ){
                Column(modifier = Modifier) {
                    Spacer(modifier = Modifier.padding(32.dp))
                    Card(modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ) {
                        Scaffold(
                            topBar = {
                                Card(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                                    colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                        MaterialTheme.colorScheme.background
                                    } else {
                                        CoinvestBase
                                    })) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, end = 16.dp, start = 16.dp)) {
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Text(text = "Judul Berita", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if(isSystemInDarkTheme()){
                                            CoinvestBase
                                        } else {
                                            CoinvestBlack
                                        })

                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp),
                                            colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                                                MaterialTheme.colorScheme.background
                                            } else {
                                                CoinvestBase
                                            }),
                                            border = BorderStroke(1.dp, CoinvestBorder)
                                        ){
                                            Column(modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                                verticalArrangement = Arrangement.Center
                                                ) {
                                                Spacer(modifier = Modifier.padding(1.dp))
                                                TransparentTextField(
                                                    text = header.value,
                                                    onValueChange = {
                                                        if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                                            header.value = it
                                                        }},
                                                    onFocusChange = {}
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Divider()
                                    }
                                }
                            },
                            content = {
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)){
                                    item {
                                        Spacer(modifier = Modifier.padding(60.dp))
                                    }
                                    item {
                                        TransparentTextField(text = content.value,
                                            onValueChange = {
                                                if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                                    content.value = it
                                                }},
                                            onFocusChange = {})
                                    }
                                }
                            },
                            bottomBar = {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){

                                }
                            },
                        )
                    }
                }
            }
        }
    )
}