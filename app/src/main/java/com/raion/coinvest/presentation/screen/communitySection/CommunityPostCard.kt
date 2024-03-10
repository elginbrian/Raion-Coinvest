package com.raion.coinvest.presentation.screen.communitySection

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
//@Preview
fun CommunityPostCard(
    articleDataClass: ArticleDataClass,
    onClick: () -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 400.dp)
        .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(CoinvestLightGrey)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier.size(36.dp), shape = CircleShape) {
                    AsyncImage(
                        model = articleDataClass.articleAuthor.profilePicture, contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                        )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = articleDataClass.articleAuthor.userName.toString(), fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = articleDataClass.articleAuthor.accountType.toString(), fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = articleDataClass.articleContent, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)) {
                AsyncImage(model = articleDataClass.imageUri, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Icon(painter = painterResource(id = R.drawable.like_icon), contentDescription = "Like", modifier = Modifier.scale(0.7f))
                Icon(painter = painterResource(id = R.drawable.comment_icon), contentDescription = "Comment", modifier = Modifier.scale(0.7f))
                Icon(painter = painterResource(id = R.drawable.bookmark_icon), contentDescription = "Bookmark", modifier = Modifier.scale(0.7f))
                Icon(painter = painterResource(id = R.drawable.share_icon), contentDescription = "Share", modifier = Modifier.scale(0.7f))
            }
        }
    }
}