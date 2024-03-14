package com.raion.coinvest.presentation.screen.mentorSection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.data.remote.firestore.model.CourseDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.designSystem.CoinvestLightGrey

@Composable
//@Preview
fun CourseCard(
    course: CourseDataClass,
    onClick: (String) -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp),
        colors = CardDefaults.cardColors(CoinvestLightGrey)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(text = course.courseName, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = "By " + course.courseOwner.userName, fontSize = 10.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = course.courseDescription, fontSize = 12.sp, lineHeight = 13.sp)
            }

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Rp."+course.coursePrice)

                Card(modifier = Modifier
                    .width(70.dp)
                    .height(25.dp),
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onClick(course.courseId)
                    }, contentAlignment = Alignment.Center){
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