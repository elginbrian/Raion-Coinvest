package com.raion.coinvest.presentation.communitySection

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import java.time.LocalDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityPostReply(
    viewModel: CommunityViewModel,
    articleList: MutableList<ArticleDataClass>,
    articleId: String,
    onTapPost: () -> Unit
){
    val thisArticle   = articleList.filter { article -> article.articleId.equals(articleId) }
    val replyList     = remember { mutableStateOf<MutableList<CommentDataClass>>(mutableListOf()) }
    viewModel.getComment { replyList.value = it }
    val thisReplyList = replyList.value.filter { comment -> comment.parentId.equals(articleId) }

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
                                articleDataClass = ArticleDataClass(
                                    articleId        = thisArticle[0].articleId,
                                    articleTitle     = thisArticle[0].articleTitle,
                                    articleAuthor    = thisArticle[0].articleAuthor,
                                    articleCreatedAt = thisArticle[0].articleCreatedAt,
                                    articleContent   = thisArticle[0].articleContent,
                                    imageUri         = thisArticle[0].imageUri
                                ),
                                onClick = {
                                    onTapPost()
                                }
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Image(painter = painterResource(id = R.drawable.membalas_divider), contentDescription = "membalas", modifier = Modifier.fillMaxWidth())
                        }
                        items(thisReplyList){
                            Spacer(modifier = Modifier.padding(8.dp))
                            CommunityPostCard(articleDataClass = ArticleDataClass(
                                articleId        = it.commentId,
                                articleAuthor    = it.commentAuthor,
                                articleContent   = it.commentContent,
                                articleCreatedAt = it.commentCreatedAt,
                                articleTitle     = "",
                                imageUri         = it.imageUri
                            )) {}
                        }
                  }
        },
    )
}