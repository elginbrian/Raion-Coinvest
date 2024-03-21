package com.raion.coinvest.presentation.screen.mentorSection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
//@Preview
fun MentorCard(
    course: CourseDataClass,
    onClick: (String) -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp),
        colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
            CoinvestBlack
        } else {
            CoinvestLightGrey
        })
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Card(modifier = Modifier
                .width(110.dp)
                .height(130.dp)) {
                AsyncImage(model = course.courseOwner.profilePicture, contentDescription = "profile picture", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = course.courseOwner.userName.toString(), fontSize = 18.sp, fontWeight = FontWeight.Medium, color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = course.courseName, fontSize = 12.sp, lineHeight = 14.sp, color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Rp."+course.coursePrice, color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })

                    Card(modifier = Modifier
                        .width(70.dp)
                        .height(25.dp)
                        .clickable (
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ){
                            onClick(course.courseId)
                        },
                        colors = CardDefaults.cardColors(CoinvestDarkPurple),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(
                                text = "Lihat", fontSize = 14.sp,
                                color = CoinvestBase
                            )
                        }
                    }
                }
            }
        }
    }
}