package com.raion.coinvest.presentation.screen.userProfileSection

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostCard
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestGrey
import com.raion.coinvest.presentation.designSystem.CoinvestLightBlue
import com.raion.coinvest.presentation.designSystem.CoinvestLightPurple
import com.raion.coinvest.presentation.designSystem.CoinvestPurple

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(
    viewModel: UserViewModel,
    user: UserDataClass,
    onChangeTab: (Int) -> Unit,
    onCreatePost: () -> Unit
){
    val articleList = remember { mutableStateOf<MutableList<PostDataClass>>(mutableListOf()) }
    val likeList    = remember { mutableStateOf<MutableList<LikeDataClass>>(mutableListOf()) }
    val commentList = remember { mutableStateOf<MutableList<CommentDataClass>>(mutableListOf()) }
    viewModel.getPost(){ articleList.value = it }
    viewModel.getLike(){
        likeList.value = it
        Log.d("LikeList", likeList.value.toString())
    }
    viewModel.getComment {
        commentList.value = it
    }


    val tabIndex = remember {
        mutableStateOf(0)
    }
    val userLike = likeList.value.filter { like -> like.userId.equals(Firebase.auth.currentUser?.uid) }


    Scaffold(
        containerColor = CoinvestLightPurple,
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)) {
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.90f),
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
                                        Spacer(modifier = Modifier.padding(6.dp))
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                            Card(modifier = Modifier
                                                .width(70.dp)
                                                .height(25.dp),
                                                colors = CardDefaults.cardColors(CoinvestDarkPurple),
                                                shape = RoundedCornerShape(50.dp)
                                            ) {
                                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                    Text(
                                                        text = "Ikuti", fontSize = 14.sp,
                                                        color = CoinvestBase
                                                    )
                                                }
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(12.dp))
                                        Text(text = user.userName.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color =  if(isSystemInDarkTheme()){
                                            CoinvestBase
                                        } else {
                                            CoinvestBlack
                                        })
                                        Text(text = user.email.toString(), fontSize = 12.sp, color =  if(isSystemInDarkTheme()){
                                            CoinvestBase
                                        } else {
                                            CoinvestBlack
                                        })

                                        Spacer(modifier = Modifier.padding(8.dp))
                                        Row(modifier = Modifier.width(120.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "Diikuti", fontSize = 12.sp, color =  if(isSystemInDarkTheme()){
                                                    CoinvestBase
                                                } else {
                                                    CoinvestBlack
                                                })
                                                Text(text = "XX", fontSize = 10.sp, color =  if(isSystemInDarkTheme()){
                                                    CoinvestBase
                                                } else {
                                                    CoinvestBlack
                                                })
                                            }
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Text(text = "Pengikut", fontSize = 12.sp, color =  if(isSystemInDarkTheme()){
                                                    CoinvestBase
                                                } else {
                                                    CoinvestBlack
                                                })
                                                Text(text = "XX", fontSize = 10.sp, color =  if(isSystemInDarkTheme()){
                                                    CoinvestBase
                                                } else {
                                                    CoinvestBlack
                                                })
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(4.dp))
                                    }

                                    UserProfileTabRow(){
                                        tabIndex.value = it
                                    }
                                }
                            },
                            content = {
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)){
                                    item {
                                        Spacer(modifier = Modifier.height(230.dp))
                                    }
                                    item {
                                        if(articleList.value.isEmpty()){
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                                CircularProgressIndicator(color = CoinvestDarkPurple)
                                            }
                                        }
                                    }
                                    if(tabIndex.value == 0) {
                                        items(articleList.value) {
                                            if (it.postAuthor.userId == user.userId) {
                                                Spacer(modifier = Modifier.padding(8.dp))
                                                CommunityPostCard(
                                                    postDataClass = it,
                                                    currentUserId = Firebase.auth.currentUser?.toString(),
                                                    likeList = likeList.value,
                                                    onClick = {},
                                                    onTapLike = {

                                                    },
                                                    onTapProfile = {

                                                    }
                                                )
                                            }
                                        }
                                    } else if(tabIndex.value == 1){
                                        items(commentList.value){
                                            if(it.commentAuthor.userId.equals(user.userId)){
                                                Spacer(modifier = Modifier.padding(8.dp))
                                                CommunityPostCard(
                                                    postDataClass = PostDataClass(
                                                        postId = it.commentId,
                                                        postAuthor = it.commentAuthor,
                                                        postCreatedAt = it.commentCreatedAt,
                                                        postContent = it.commentContent,
                                                        communityId = "",
                                                        imageUri = it.imageUri
                                                    ),
                                                    currentUserId = user.userId,
                                                    likeList = likeList.value,
                                                    onClick = {},
                                                    onTapLike = {

                                                    },
                                                    onTapProfile = {

                                                    }
                                                )
                                            }
                                        }

                                    } else if(tabIndex.value == 2) {
                                        items(articleList.value) {
                                            for(like in userLike){
                                                if (it.postId.equals(like.parentId) && like.userId.equals(user.userId)) {
                                                    Spacer(modifier = Modifier.padding(8.dp))
                                                    CommunityPostCard(
                                                        postDataClass = it,
                                                        currentUserId = user.userId,
                                                        likeList = likeList.value,
                                                        onClick = {},
                                                        onTapLike = {

                                                        },
                                                        onTapProfile = {

                                                        }
                                                        )
                                                }
                                            }
                                        }
                                    }
                                    item {
                                        Spacer(modifier = Modifier.height(160.dp))
                                    }
                                }
                            },
                            bottomBar = {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                                    AppsBottomBar(){
                                        onChangeTab(it)
                                    }
                                }
                            },
                            floatingActionButton = {
                                Card(
                                    modifier = Modifier
                                        .width(106.dp)
                                        .height(46.dp)
                                        .clickable(
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() } // This is mandatory
                                        ) { onCreatePost() },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = CardDefaults.cardColors(CoinvestDarkPurple)
                                ) {
                                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post", tint = CoinvestBase)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(text = "Post", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                                    }
                                }
                            }
                        )
                    }
                }
                Row() {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Card(modifier = Modifier.size(120.dp),
                        shape = CircleShape
                    ){
                        AsyncImage(model = user.profilePicture, contentDescription = "profile picture", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    }
                }

            }

        }
    )
}