package com.raion.coinvest.presentation.screen.roleSection.authorVerifSection

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.R
import com.raion.coinvest.data.remote.firebaseStorage.model.VerifDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import java.util.UUID

@Composable
//@Preview
fun BerkasPengalaman(
    onNextPage: (VerifDataClass) -> Unit
) {
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Image(
            painter = painterResource(id = R.drawable.backgroundgradient),
            contentDescription = "Deskripsi Gambar",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .width(200.dp)
                .height(200.dp)
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally)
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint = Color.White)
                        Text(text = "Verifikasi Berkas", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(painter = painterResource(id = R.drawable.num123), contentDescription = "", modifier = Modifier.fillMaxWidth(0.5f))
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Pengalaman", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "1) Isi dengan format JPG, PNG,  atau PDF", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                        Text(text = "2) Tulis dengan format nama_pengalaman.pdf", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                        Text(text = "3) Data tidak lebih dari 2 MB", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                    }
                }
                if(selectedImageUri.value != null){
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(modifier = Modifier
                        .width(320.dp)
                        .height(160.dp)
                    ) {
                        AsyncImage(model = selectedImageUri.value, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    }
                }

                Card (modifier = Modifier
                    .height(160.dp)
                    .width(320.dp),colors = CardDefaults.cardColors(Color.White)) {
                    Box(modifier = Modifier.fillMaxSize().clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }, contentAlignment = Alignment.Center){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.tandatambah),
                                contentDescription = "Tanda Tambah", modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Card (modifier = Modifier
                                .width(160.dp)
                                .height(40.dp),border = BorderStroke(1.dp, CoinvestDarkPurple),colors = CardDefaults.cardColors(Color.White)) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, ){
                                    Text(text = "Tambah File", fontSize = 14.sp, color = CoinvestDarkPurple)
                                }
                            }
                        }
                    }
                }
                
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), contentAlignment = Alignment.Center){
                    Card (modifier = Modifier
                        .width(266.dp)
                        .height(62.dp),colors = CardDefaults.cardColors(Color.White)) {
                        Box(modifier = Modifier.fillMaxSize().clickable {
                            onNextPage(
                                VerifDataClass(
                                userId = Firebase.auth.currentUser?.uid ?: UUID.randomUUID().toString(),
                                accountType = "author",
                                document1 = selectedImageUri.value ?: Uri.EMPTY,
                                document2 = Uri.EMPTY
                            )
                            )
                        }, contentAlignment = Alignment.Center){
                            Text(text = "Simpan dan lanjutkan", fontSize = 16.sp, color = CoinvestDarkPurple)
                        }
                    }
                }
            }
        }
    }
}