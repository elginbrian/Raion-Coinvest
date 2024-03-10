package com.raion.coinvest.presentation.screen.communitySection

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField
import java.time.LocalDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityPostReplying(
    viewModel: CommunityViewModel,
    articleList: MutableList<ArticleDataClass>,
    articleId: String,
    onUploadReply: (CommentDataClass) -> Unit
){
    val thisArticle   = articleList.filter { article -> article.articleId.equals(articleId) }

    val content          = remember { mutableStateOf("") }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )

    Scaffold(
        containerColor = CoinvestBase,
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back button"
                        )
                        Text(
                            text = "Forum & Komunitas",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(20.dp))

                    }
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)){

                item(){
                    Spacer(modifier = Modifier.padding(40.dp))
                    CommunityPostCard(
                        articleDataClass = ArticleDataClass(
                            articleId        = thisArticle[0].articleId,
                            articleTitle     = thisArticle[0].articleTitle,
                            articleAuthor    = thisArticle[0].articleAuthor,
                            articleCreatedAt = thisArticle[0].articleCreatedAt,
                            articleContent   = thisArticle[0].articleContent,
                            imageUri         = thisArticle[0].imageUri
                        )
                    ){}
                    Spacer(modifier = Modifier.padding(8.dp))
                    Image(painter = painterResource(id = R.drawable.membalas_divider), contentDescription = "membalas", modifier = Modifier.fillMaxWidth())
                }
                
                item(){
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(700.dp),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        colors = CardDefaults.cardColors(CoinvestLightGrey)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(modifier = Modifier.size(36.dp), shape = CircleShape) {
                                    AsyncImage(
                                        model = Firebase.auth.currentUser?.photoUrl.toString(),
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = Firebase.auth.currentUser?.displayName.toString()
                                    , fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                            }

                            if(selectedImageUri.value != null){
                                Spacer(modifier = Modifier.height(16.dp))
                                Card(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)) {
                                    AsyncImage(model = selectedImageUri.value, contentDescription = "", contentScale = ContentScale.Crop)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            TransparentTextField(
                                text = content.value,
                                onValueChange = {
                                    if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                                        content.value = it
                                    }
                                }, onFocusChange = {})
                        }
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = CircleShape
                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Icon(imageVector = Icons.Rounded.Image, contentDescription = "pick image", tint = CoinvestBase,)
                    }
                }

                Card(modifier = Modifier
                    .width(70.dp)
                    .height(30.dp)
                    .clickable {
                        onUploadReply(
                            CommentDataClass(
                            commentId        = UUID.randomUUID().toString(),
                            parentId         = thisArticle[0].articleId,
                            commentContent   = content.value,
                            commentCreatedAt = LocalDateTime.now().toString(),
                            commentAuthor    = UserDataClass(
                                userId = Firebase.auth.currentUser?.uid,
                                userName = Firebase.auth.currentUser?.displayName,
                                profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                accountType = "author",
                                email = Firebase.auth.currentUser?.email
                                ),
                                imageUri = selectedImageUri.value ?: Uri.EMPTY
                            )
                        )
                        selectedImageUri.value = null
                        content.value = ""
                    },
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = RoundedCornerShape(50.dp)
                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(
                            text = "Post", fontSize = 14.sp,
                            color = CoinvestBase
                        )
                    }
                }
            }
        }
    )
}