package com.raion.coinvest.presentation.screen.homeSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkGrey
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
fun CompactCourseCard(
    course: CourseDataClass,
    onClick: (String) -> Unit
){
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(260.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            if(isSystemInDarkTheme()){
                CoinvestBlack
            } else {
                CoinvestLightGrey
            }
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(model = course.courseOwner.profilePicture, contentDescription = "course banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp).clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), contentScale = ContentScale.Crop)
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = course.courseName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    }
                )
                Text(
                    text = "Oleh " + course.courseOwner.userName.toString(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    }
                )
            }


            Card(
                modifier = Modifier
                    .width(80.dp)
                    .height(32.dp),
                colors = CardDefaults.cardColors(CoinvestDarkPurple),
                shape = RoundedCornerShape(120.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().clickable {
                                                                onClick(course.courseId)
                }, contentAlignment = Alignment.Center){
                    Text(
                        text = "Tonton",
                        color = Color.White,
                    )
                }
            }
        }
    }
}