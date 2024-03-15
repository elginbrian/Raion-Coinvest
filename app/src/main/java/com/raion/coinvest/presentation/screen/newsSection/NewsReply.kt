package com.raion.coinvest.presentation.screen.newsSection

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.screen.communitySection.CommunityPostCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsReply(
    newsId: String,
    viewModel: NewsViewModel,
    onTapFloatingButton: () -> Unit
){
    val commentList = remember {
        mutableStateOf<MutableList<CommentDataClass>>(mutableListOf())
    }
    viewModel.getComment {
        commentList.value = it
    }

    val likeList    = remember { mutableStateOf<MutableList<LikeDataClass>>(mutableListOf()) }
    viewModel.getLike(){ likeList.value = it }

    val thisNewsComment = commentList.value.filter { comment -> comment.parentId.equals(newsId) }

    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
            ){
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
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button")
                        Text(text = "Komentar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(20.dp))
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
                        }
                        items(thisNewsComment) {
                            Spacer(modifier = Modifier.padding(8.dp))
                            CommunityPostCard(postDataClass = PostDataClass(
                                postId = it.commentId,
                                postContent = it.commentContent,
                                postAuthor = it.commentAuthor,
                                postCreatedAt = it.commentCreatedAt,
                                imageUri = it.imageUri,
                                communityId = "",
                            ),
                                onClick = {

                                },
                                currentUserId = Firebase.auth.currentUser?.uid,
                                likeList = likeList.value,
                                onTapLike = {
                                    if(it.second == true){
                                        viewModel.addLike(it.first)
                                    } else {
                                        viewModel.deleteLike(it.first)
                                    }
                                }
                            )
                        }
                      item {
                          Spacer(modifier = Modifier.padding(60.dp))
                      }
                  }
        },
        floatingActionButton = {
            Card(
                modifier = Modifier
                    .width(130.dp)
                    .height(46.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) { onTapFloatingButton() },
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(CoinvestDarkPurple)
            ) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post", tint = CoinvestBase)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Komentar", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    )
}