package com.raion.coinvest.presentation.screen.communitySection

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.raion.coinvest.data.remote.firestore.model.LikeDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.presentation.widget.appsBottomBar.AppsBottomBar
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.widget.searchBar.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//@Preview
fun CommunityScreen(
    viewModel: CommunityViewModel,
    onTapSearch: () -> Unit,
    onChangeTab: (Int) -> Unit,
    onTapFloatingButton: () -> Unit,
    onTapPost: (Pair<MutableList<PostDataClass>,String>) -> Unit,
){
    val articleList = remember { mutableStateOf<MutableList<PostDataClass>>(mutableListOf()) }
    val likeList    = remember { mutableStateOf<MutableList<LikeDataClass>>(mutableListOf()) }
    viewModel.getPost(){ articleList.value = it }
    LaunchedEffect(key1 = null){
        viewModel.getLike(){
            likeList.value = it
            Log.d("LikeList", likeList.value.toString())
        }
    }


    Scaffold(
        containerColor = CoinvestBase,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(CoinvestBase)
            ) {
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
                        Text(text = "Forum & Komunitas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                    SearchBar(){
                        onTapSearch()
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .padding(end = 100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        CommunityTabRow()
                    }
                }
            }
        },
        content = {
             LazyColumn(modifier = Modifier
                 .fillMaxSize()
                 .padding(horizontal = 16.dp)){
                item { 
                    Spacer(modifier = Modifier.height(170.dp))
                }
                item {
                    if(articleList.value.isEmpty()){
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            CircularProgressIndicator(color = CoinvestDarkPurple)
                        }
                    }
                }
                items(articleList.value){
                    Spacer(modifier = Modifier.height(8.dp))
                    CommunityPostCard(
                        currentUserId = Firebase.auth.currentUser?.uid,
                        postDataClass = it,
                        likeList = likeList.value,
                        onClick = {
                            onTapPost(Pair(articleList.value, it.postId))
                        },
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
                     Spacer(modifier = Modifier.height(170.dp))
                 }
             }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)){
                AppsBottomBar(currentTab = 3){
                    onChangeTab(it)
                }
            }

        },
        floatingActionButton = {
            Card(
                modifier = Modifier
                    .width(106.dp)
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
                    Text(text = "Post", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    )
}