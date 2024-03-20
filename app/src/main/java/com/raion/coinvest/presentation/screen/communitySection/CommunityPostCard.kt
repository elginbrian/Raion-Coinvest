package com.raion.coinvest.presentation.screen.communitySection

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey
import com.raion.coinvest.presentation.widget.likeButton.LikeButton
import com.raion.coinvest.presentation.widget.shareButton.ShareButton

@Composable
//@Preview
fun CommunityPostCard(
    currentUserId: String?,
    postDataClass: PostDataClass,
    likeList: MutableList<LikeDataClass>,
    onClick: () -> Unit,
    onTapLike: (Pair<LikeDataClass, Boolean>) -> Unit,
    onTapProfile: (UserDataClass) -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 420.dp)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() } // This is mandatory
        ) { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
            CoinvestBlack
        } else {
            CoinvestLightGrey
        })
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier.size(36.dp), shape = CircleShape) {
                    AsyncImage(
                        model = postDataClass.postAuthor.profilePicture, contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize().clickable {
                                                         onTapProfile(postDataClass.postAuthor)
                        }, contentScale = ContentScale.Crop
                        )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = postDataClass.postAuthor.userName.toString(), fontSize = 12.sp, color =  if(isSystemInDarkTheme()){
                    CoinvestBase
                } else {
                    CoinvestBlack
                })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = postDataClass.postCreatedAt.substring(0,10).toString(), fontSize = 10.sp, color =  if(isSystemInDarkTheme()){
                    CoinvestBase
                } else {
                    CoinvestBlack
                })
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = postDataClass.postContent, fontSize = 14.sp, maxLines = 12, color =  if(isSystemInDarkTheme()){
                CoinvestBase
            } else {
                CoinvestBlack
            })
            Spacer(modifier = Modifier.height(16.dp))

            if(postDataClass.imageUri != Uri.EMPTY){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)) {
                    AsyncImage(model = postDataClass.imageUri, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                LikeButton(userId = currentUserId, parentId = postDataClass.postId, likeList = likeList) {
                    onTapLike(it)
                }
                Icon(painter = painterResource(id = R.drawable.comment_icon), contentDescription = "Comment", modifier = Modifier.scale(0.7f), tint =  if(isSystemInDarkTheme()){
                    CoinvestBase
                } else {
                    CoinvestBlack
                })
                Icon(painter = painterResource(id = R.drawable.bookmark_icon), contentDescription = "Bookmark", modifier = Modifier.scale(0.7f), tint =  if(isSystemInDarkTheme()){
                    CoinvestBase
                } else {
                    CoinvestBlack
                })
                ShareButton(content = postDataClass.postContent, owner = postDataClass.postAuthor.userName.toString(), date = postDataClass.postCreatedAt)
            }
        }
    }
}