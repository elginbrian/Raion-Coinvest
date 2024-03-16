package com.raion.coinvest.presentation.widget.likeButton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple

@Composable
fun LikeButton(
    userId: String?,
    parentId: String,
    likeList: MutableList<LikeDataClass>,
    onTapLike: (Pair<LikeDataClass, Boolean>) -> Unit
){
    val thisPostLike = likeList.filter { like -> like.parentId.equals(parentId) }
    val isUserLiking = thisPostLike.filter { like -> like.userId.equals(userId) }

    val likeState = remember {
        mutableStateOf(false)
    }

    if(isUserLiking.isNotEmpty()){
        likeState.value = true
    }


    Row(verticalAlignment = Alignment.CenterVertically) {
       Text(text = thisPostLike.size.toString())
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(painter = painterResource(id = if(likeState.value){
            R.drawable.likefull_icon
        } else {
            R.drawable.like_icon
        }

        ), contentDescription = "Like", modifier = Modifier
            .scale(0.7f)
            .clickable {
                likeState.value = !likeState.value
                onTapLike(
                    Pair(LikeDataClass(
                        parentId = parentId,
                        userId = userId
                    ), likeState.value)
                )
            }
            , tint = if(likeState.value){
                CoinvestDarkPurple
            } else {
                CoinvestBlack
            }
        )
    }

}