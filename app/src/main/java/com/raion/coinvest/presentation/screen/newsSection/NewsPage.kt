package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestBorder
import com.raion.coinvest.presentation.widget.likeButton.LikeButton
import com.raion.coinvest.presentation.widget.shareButton.ShareButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsPage(
    viewModel: NewsViewModel,
    newsList: MutableList<NewsDataClass>,
    newsId: String,
    onClickComment: (String) -> Unit,
    onTapProfile: (UserDataClass) -> Unit,
    onTapBack: () -> Unit
){
    val thisNews = newsList.filter { news -> news.newsId.equals(newsId) }
    val likeList    = remember { mutableStateOf<MutableList<LikeDataClass>>(mutableListOf()) }
    LaunchedEffect(key1 = null){
        viewModel.getLike(){
            likeList.value = it
            Log.d("LikeList", likeList.value.toString())
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", modifier = Modifier.clickable { onTapBack() })

                    Icon(imageVector = Icons.Rounded.Flag, contentDescription = "Back button")
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item{
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp), contentAlignment = Alignment.BottomCenter) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
                            ){
                                AsyncImage(model = thisNews[0].imageUri, contentDescription = "Banner", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(160.dp),
                            colors = CardDefaults.cardColors(if (isSystemInDarkTheme()){
                                CoinvestBlack
                            } else {
                                Color.Transparent
                            }),
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, CoinvestBorder),
                            //elevation = CardDefaults.cardElevation(1.dp)
                        ){
                            Box(modifier = Modifier.fillMaxSize()){
                                if(!isSystemInDarkTheme()){
                                    Image(painter = painterResource(id = R.drawable.blur_background), contentDescription = "blur", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop,)
                                }
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(text = thisNews[0].newsCreatedAt.substring(0,10), fontSize = 12.sp, fontWeight = FontWeight.Medium, color =  if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = thisNews[0].newsTitle, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color =  if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                    Text(text = "Diunggah pada xx menit yang lalu", fontSize = 12.sp, fontWeight = FontWeight.Normal, color =  if(isSystemInDarkTheme()){
                                        CoinvestBase
                                    } else {
                                        CoinvestBlack
                                    })
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    onTapProfile(thisNews[0].newsAuthor)
                                }, shape = CircleShape) {
                                AsyncImage(model = thisNews[0].newsAuthor.profilePicture, contentDescription = "profile picture")
                            }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = thisNews[0].newsAuthor.userName.toString(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        LikeButton(userId = Firebase.auth.currentUser?.uid, parentId = thisNews[0].newsId,
                            likeList = likeList.value,
                            onTapLike ={
                                if(it.second == true){
                                    viewModel.addLike(it.first)
                                } else {
                                    viewModel.deleteLike(it.first)
                                }
                            }
                        )
                        Icon(painter = painterResource(id = R.drawable.comment_icon), contentDescription = "Comment", modifier = Modifier
                            .scale(0.7f)
                            .clickable {
                                onClickComment(thisNews[0].newsId)
                            })
                        Icon(painter = painterResource(id = R.drawable.bookmark_icon), contentDescription = "Bookmark", modifier = Modifier.scale(0.7f))
                        ShareButton(header = thisNews[0].newsTitle, content = thisNews[0].newsContent, owner = thisNews[0].newsAuthor.userName.toString(), date = thisNews[0].newsCreatedAt)
                    }
                }

                item() {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)) {
                        Text(text = thisNews[0].newsContent, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Justify)
                    }
                    Spacer(modifier = Modifier.padding(40.dp))
                }
            }
        }
    )
}