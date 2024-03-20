package com.raion.coinvest.presentation.screen.mentorSection

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Menu
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun MentorNew(
    onTapLanjutkan: (CourseDataClass) -> Unit
){
    val title       = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val price       = remember { mutableStateOf("") }
    val category    = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                    MaterialTheme.colorScheme.background
                } else {
                    CoinvestBase
                })
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(text = "Upload Mentorship", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(modifier = Modifier
                            .width(88.dp)
                            .height(26.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onTapLanjutkan(
                                    CourseDataClass(
                                        courseId = UUID.randomUUID().toString(),
                                        courseName = title.value,
                                        courseDescription = description.value,
                                        coursePrice = price.value,
                                        courseCategory = category.value,
                                        courseOwner = UserDataClass(
                                            userId = Firebase.auth.currentUser?.uid,
                                            userName = Firebase.auth.currentUser?.displayName,
                                            profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                            accountType = "author",
                                            email = Firebase.auth.currentUser?.email
                                        ),
                                        courseContent = mutableListOf()
                                )
                                )
                            },
                            colors = CardDefaults.cardColors(CoinvestBase),
                            border = BorderStroke(1.dp, CoinvestBorder),
                            shape = RoundedCornerShape(50.dp)
                        ){
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Text(
                                    text = "Lanjutkan", fontSize = 12.sp,
                                    color = CoinvestBlack,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu", tint =  if(isSystemInDarkTheme()){
                            CoinvestBase
                        } else {
                            CoinvestBlack
                        })
                    }
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)){

                item {
                    Spacer(modifier = Modifier.padding(40.dp))
                    Text(text = "Judul Series", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                                text = title.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        title.value = it
                                    }},
                                onFocusChange = {}
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Deskripsi Series", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                                text = description.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        description.value = it
                                    }},
                                onFocusChange = {}
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Harga", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                                text = price.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        price.value = it
                                    }},
                                onFocusChange = {}
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Kategori Series", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                                text = category.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        category.value = it
                                    }},
                                onFocusChange = {}
                            )
                        }
                    }
                }
            }
        }
    )
}