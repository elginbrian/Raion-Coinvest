package com.raion.coinvest.presentation.screen.debugging

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.CourseContent
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlue
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import com.raion.coinvest.presentation.widget.videoPlayerCard.VideoPlayerCard
import java.util.UUID

/*
Ini file buat aku (Elgin) ngetest data dari back-end nya,
Gaada hubungannya sama mockup UI/UX,
nanti pas project kelar bakal dihapus
*/

@Composable
fun DebugScreen5(
    viewModel: DebugViewModel,
    viewModel2: DebugViewModel2,
    onUploadVideo: (CourseDataClass) -> Unit
){
    val selectedVideoUri = remember { mutableStateOf<Uri?>(null) }
    val singleVideoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            //uri?.let(viewModel::addVideoUri)
            selectedVideoUri.value = uri
        }
    )
    val header      = remember { mutableStateOf("") }
    val content     = remember { mutableStateOf("") }
    val courseList  = remember { mutableStateOf<MutableList<CourseDataClass>>(mutableListOf()) }

    LaunchedEffect(key1 = true) {
        viewModel2.getCourse {
            courseList.value = it
            Log.d("", courseList.value.toString())
        }
//        viewModel2.getListWithMarketData {
//            Log.d("Crypto", it.toString())
//        }
//        viewModel2.getTrendingSearchList {
//            Log.d("Crypto", it.toString())
//        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(CoinvestBase)){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)){

            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        OutlinedTextField(value = header.value, onValueChange = { header.value = it })
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = content.value, onValueChange = { content.value = it }, modifier = Modifier.height(150.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .width(280.dp)
                                .clickable {
                                    singleVideoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
                                    )
                                },
                            colors = CardDefaults.cardColors(CoinvestPurple)
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Text(text = selectedVideoUri.value.toString(), color = CoinvestBase)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .width(280.dp)
                                .clickable {
                                    onUploadVideo(
                                        CourseDataClass(
                                            courseId = UUID
                                                .randomUUID()
                                                .toString(),
                                            courseName = header.value,
                                            courseOwner = UserDataClass(
                                                userId = Firebase.auth.currentUser?.uid,
                                                userName = Firebase.auth.currentUser?.displayName,
                                                profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                                accountType = "author",
                                                email = Firebase.auth.currentUser?.email
                                            ),
                                            courseContent = mutableListOf(
                                                CourseContent(
                                                    videoTitle = header.value,
                                                    videoDescription = content.value,
                                                    videoUri = selectedVideoUri.value ?: Uri.EMPTY
                                                )
                                            )
                                        )
                                    )
                                },
                            colors = CardDefaults.cardColors(CoinvestBlue)
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Text(text = "Upload", color = CoinvestBase)
                            }
                        }
                    }
                }
            }

            items(courseList.value){
                Spacer(modifier = Modifier.height(16.dp))
                CourseCard(
                    viewModel = viewModel,
                    header    = it.courseName,
                    content   = it.courseOwner.userName.orEmpty(),
                    videoUri  = it.courseContent[0].videoUri
                )
            }
        }
    }
}

@Composable
fun CourseCard(
    viewModel: DebugViewModel,
    header: String = "Lorem Ipsum",
    content: String = "Lorem Ipsum",
    videoUri: Uri = Uri.EMPTY
){
    viewModel.addVideoUri(videoUri)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = header)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content)
            Spacer(modifier = Modifier.height(8.dp))

            VideoPlayerCard()
        }
    }
}