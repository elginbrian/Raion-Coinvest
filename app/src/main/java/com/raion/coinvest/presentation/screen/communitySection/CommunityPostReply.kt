package com.raion.coinvest.presentation.screen.communitySection

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityPostReply(
    viewModel: CommunityViewModel,
    articleList: MutableList<PostDataClass>,
    articleId: String,
    onTapPost: () -> Unit,
    onTapProfile: (UserDataClass) -> Unit
){
    val thisArticle   = articleList.filter { article -> article.postId.equals(articleId) }
    val replyList     = remember { mutableStateOf<MutableList<CommentDataClass>>(mutableListOf()) }
    viewModel.getComment { replyList.value = it }
    val thisReplyList = replyList.value.filter { comment -> comment.parentId.equals(articleId) }

    val likeList    = remember { mutableStateOf<MutableList<LikeDataClass>>(mutableListOf()) }
    viewModel.getLike(){ likeList.value = it }

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
                      .padding(16.dp)){
                        item(){
                            Spacer(modifier = Modifier.padding(40.dp))
                            CommunityPostCard(
                                postDataClass = PostDataClass(
                                    postId        = thisArticle[0].postId,
                                    communityId     = thisArticle[0].communityId,
                                    postAuthor    = thisArticle[0].postAuthor,
                                    postCreatedAt = thisArticle[0].postCreatedAt,
                                    postContent   = thisArticle[0].postContent,
                                    imageUri         = thisArticle[0].imageUri
                                ),
                                onClick = {
                                    onTapPost()
                                },
                                currentUserId = Firebase.auth.currentUser?.uid,
                                likeList = likeList.value,
                                onTapLike = {
                                    if(it.second == true){
                                        viewModel.addLike(it.first)
                                    } else {
                                        viewModel.deleteLike(it.first)
                                    }
                                },
                                onTapProfile = {user ->
                                    onTapProfile(user)
                                }
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Image(painter = painterResource(id = R.drawable.membalas_divider), contentDescription = "membalas", modifier = Modifier.fillMaxWidth())
                        }
                        items(thisReplyList){
                            Spacer(modifier = Modifier.padding(8.dp))
                            CommunityPostCard(postDataClass = PostDataClass(
                                postId        = it.commentId,
                                postAuthor    = it.commentAuthor,
                                postContent   = it.commentContent,
                                postCreatedAt = it.commentCreatedAt,
                                communityId     = "",
                                imageUri         = it.imageUri
                            ),
                                onClick = {
                                          //TODO
                                },
                                currentUserId = Firebase.auth.currentUser?.uid,
                                likeList = likeList.value,
                                onTapLike = {
                                    if(it.second == true){
                                        viewModel.addLike(it.first)
                                    } else {
                                        viewModel.deleteLike(it.first)
                                    }
                                },
                                onTapProfile = {user ->
                                    onTapProfile(user)
                                }
                            )
                        }
                  }
        },
    )
}