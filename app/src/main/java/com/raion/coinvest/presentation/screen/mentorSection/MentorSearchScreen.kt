package com.raion.coinvest.presentation.screen.mentorSection

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MentorSearchScreen(){
    Scaffold(
        containerColor = CoinvestBase,
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
                        Text(text = "Course & Mentor", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    SearchBar()
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                }
            }
        },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)){
                item {
                    Spacer(modifier = Modifier.padding(60.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .padding(end = 230.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        MentorSearchTabRow()
                    }
                }
                items(5){
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Lorem Ipsum")
                        Icon(imageVector = Icons.Rounded.Clear, contentDescription = "Clear")
                    }
                }
            }
        },
        bottomBar = {}
    )
}