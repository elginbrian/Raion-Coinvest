package com.raion.coinvest.presentation.debugging

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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlue
import com.raion.coinvest.presentation.designSystem.CoinvestPurple
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun DebugScreen4(
    viewModel: DebugViewModel2,
    onUploadPost: (ArticleDataClass) -> Unit
){
    val header = remember {
        mutableStateOf("")
    }
    val content = remember {
        mutableStateOf("")
    }
    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )
    val getPost = remember {
        mutableStateOf(true)
    }
    val articleList = remember {
        mutableStateOf<MutableList<ArticleDataClass>>(mutableListOf())
    }

    viewModel.getPost(){
        articleList.value = it
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(CoinvestBase)){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            item {
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
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            colors = CardDefaults.cardColors(CoinvestPurple)
                            ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                Text(text = selectedImageUri.value.toString(), color = CoinvestBase)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .width(280.dp)
                                .clickable {
                                    onUploadPost(
                                        ArticleDataClass(
                                            articleId = UUID
                                                .randomUUID()
                                                .toString(),
                                            articleTitle = header.value,
                                            articleContent = content.value,
                                            articleCreatedAt = LocalDateTime
                                                .now()
                                                .toString(),
                                            articleAuthor = UserDataClass(
                                                userId = Firebase.auth.currentUser?.uid,
                                                userName = Firebase.auth.currentUser?.displayName,
                                                profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                                accountType = "author",
                                                email = Firebase.auth.currentUser?.email
                                            ),
                                            imageUri = selectedImageUri.value ?: Uri.EMPTY
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

            items(articleList.value){
                Spacer(modifier = Modifier.height(16.dp))
                PostCard(
                    header   = it.articleTitle,
                    content  = it.articleContent,
                    imageUri = it.imageUri
                )
                Log.d("items", it.toString())
            }

        }
    }
}

@Composable
fun PostCard(
    header: String  = "Lorem Ipsum",
    content: String = "Lorem Ipsum",
    imageUri: Uri
){
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
            Card(
                modifier = Modifier
                    .height(180.dp)
                    .width(320.dp)
                    .clickable { },
                colors = CardDefaults.cardColors(CoinvestPurple)
            ) {
                AsyncImage(model = imageUri, contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }
        }
    }
}