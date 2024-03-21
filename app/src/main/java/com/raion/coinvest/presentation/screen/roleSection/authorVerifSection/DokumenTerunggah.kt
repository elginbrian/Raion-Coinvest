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
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple

@Composable
//@Preview
fun DokumenTerunggah(
    prevData: VerifDataClass,
    onFinished: (Pair<VerifDataClass, UserDataClass>) -> Unit,
    onTapBack: () -> Unit
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
                        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "Back button", tint = Color.White, modifier = Modifier.clickable {
                            onTapBack()
                        })
                        Text(text = "Verifikasi Berkas", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = R.drawable.num123_3), contentDescription = "", modifier = Modifier.fillMaxWidth(0.55f))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Berkas Terunggah", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "1) Data akan diproses terlebih dahulu maksimal 2 x 24 jam", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)
                        Text(text = "2) Akan ada pemberitahuan jika data sudah berhasil diverivifikasi", modifier = Modifier.padding(start = 15.dp, end = 15.dp), color = Color.White)

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Card(modifier = Modifier
                    .width(320.dp)
                    .height(160.dp)
                ) {
                    AsyncImage(model = prevData.document1, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }
                Spacer(modifier = Modifier.height(8.dp))
                Card(modifier = Modifier
                    .width(320.dp)
                    .height(160.dp)
                ) {
                    AsyncImage(model = prevData.document2, contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), contentAlignment = Alignment.Center){
                    Card (modifier = Modifier
                        .width(266.dp)
                        .height(62.dp), colors = CardDefaults.cardColors(CoinvestBase)) {
                        Box(modifier = Modifier.fillMaxSize().clickable {
                            val verif = VerifDataClass(
                                userId      = prevData.userId,
                                accountType = "author",
                                document1   = prevData.document1,
                                document2   = prevData.document2
                            )
                            val user = UserDataClass(
                                userId      = prevData.userId,
                                userName    = Firebase.auth.currentUser?.email.toString().substringBefore('@'),
                                accountType = prevData.accountType,
                                email       = Firebase.auth.currentUser?.email,
                                profilePicture = Firebase.auth.currentUser?.photoUrl.toString()
                            )
                            onFinished(Pair(verif, user))
                        }, contentAlignment = Alignment.Center){
                            Text(text = "Simpan dan lanjutkan", fontSize = 16.sp, color = CoinvestDarkPurple)
                        }
                    }
                }
            }
        }
    }
}