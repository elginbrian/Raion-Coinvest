package com.raion.coinvest.presentation.screen.mentorSection

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField
import com.raion.coinvest.presentation.widget.videoPlayerCard.VideoPlayerCard
import java.time.LocalDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun MentorCreate(
    viewModel: MentorViewModel,
    newCourse: CourseDataClass,
    onTapPost: (CourseDataClass) -> Unit
){
    val selectedVideoUri = remember { mutableStateOf<Uri?>(null) }
    val singleVideoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let(viewModel::addVideoUri)
            selectedVideoUri.value = uri
        }
    )

    val title       = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val copyright   = remember { mutableStateOf("") }

    Scaffold(
        containerColor = CoinvestBase,
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
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
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button")
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(text = "Upload Mentorship", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(modifier = Modifier
                            .width(66.dp)
                            .height(26.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onTapPost(
                                    CourseDataClass(
                                    courseId = newCourse.courseId,
                                    courseName = newCourse.courseName,
                                    courseDescription = newCourse.courseDescription,
                                    courseCategory = newCourse.courseCategory,
                                    courseOwner = newCourse.courseOwner,
                                    coursePrice = newCourse.coursePrice,
                                    courseContent = mutableListOf(CourseContent(
                                        videoId = UUID.randomUUID().toString(),
                                        videoTitle = title.value,
                                        videoDescription = description.value,
                                        videoCopyright = copyright.value,
                                        videoUri = selectedVideoUri.value ?: Uri.EMPTY
                                    ))
                                )
                                )
                            },
                            colors = CardDefaults.cardColors(CoinvestBase),
                            border = BorderStroke(1.dp, CoinvestBorder),
                            shape = RoundedCornerShape(50.dp)
                        ){
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Text(
                                    text = "Post", fontSize = 12.sp,
                                    color = CoinvestBlack,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
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
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)) {
                        Box(modifier = Modifier
                            .fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            VideoPlayerCard(viewModel)

                            if(selectedVideoUri.value == null){
                                Card(modifier = Modifier
                                    .size(44.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        singleVideoPickerLauncher.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
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

                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Judul Video", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
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
                    Text(text = "Deskripsi Video", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
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
                    Text(text = "Series", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
                        border = BorderStroke(1.dp, CoinvestBorder)
                    ){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.padding(1.dp))
                            TransparentTextField(
                                text = newCourse.courseName,
                                onValueChange = {},
                                onFocusChange = {}
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Hak Cipta", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        colors = CardDefaults.cardColors(CoinvestBase),
                        border = BorderStroke(1.dp, CoinvestBorder)
                    ){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.padding(1.dp))
                            TransparentTextField(
                                text = copyright.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        copyright.value = it
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