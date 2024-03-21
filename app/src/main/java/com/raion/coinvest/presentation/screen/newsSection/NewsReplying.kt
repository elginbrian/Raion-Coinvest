import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.raion.coinvest.data.remote.firestore.model.CommentDataClass
import com.raion.coinvest.data.remote.firestore.model.PostDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import com.raion.coinvest.presentation.designSystem.CoinvestBase
import com.raion.coinvest.presentation.designSystem.CoinvestBlack
import com.raion.coinvest.presentation.designSystem.CoinvestDarkPurple
import com.raion.coinvest.presentation.widget.transparentTextField.TransparentTextField
import java.time.LocalDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsReplying(
    parentId: String,
    onUploadPost: (CommentDataClass) -> Unit,
    onTapBack: () -> Unit
) {
    val content          = remember { mutableStateOf("") }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri.value = uri }
    )
    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(if(isSystemInDarkTheme()){
                    MaterialTheme.colorScheme.background
                } else {
                    CoinvestBase
                })
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back button",
                            modifier = Modifier.clickable { onTapBack() }, tint =  if(isSystemInDarkTheme()){
                                CoinvestBase
                            } else {
                                CoinvestBlack
                            }
                        )
                        Text(
                            text = "Komentar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold, color =  if(isSystemInDarkTheme()){
                                CoinvestBase
                            } else {
                                CoinvestBlack
                            }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Spacer(modifier = Modifier.padding(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(modifier = Modifier.size(36.dp), shape = CircleShape) {
                        AsyncImage(
                            model = Firebase.auth.currentUser?.photoUrl.toString(), contentDescription = "Profile Picture",
                            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = Firebase.auth.currentUser?.displayName.toString(), fontSize = 12.sp, color =  if(isSystemInDarkTheme()){
                        CoinvestBase
                    } else {
                        CoinvestBlack
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                }

                if(selectedImageUri.value != null){
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)) {
                        AsyncImage(model = selectedImageUri.value, contentDescription = "", contentScale = ContentScale.Crop)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                TransparentTextField(
                    text = content.value,
                    onValueChange = {
                        if ((it.all { char -> char.isDefined() || char.isWhitespace() })) {
                            content.value = it
                        }
                    }, onFocusChange = {})
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = CircleShape
                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Icon(imageVector = Icons.Rounded.Image, contentDescription = "pick image", tint = CoinvestBase,)
                    }
                }

                Card(modifier = Modifier
                    .width(70.dp)
                    .height(30.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() } // This is mandatory
                    ) {
                        onUploadPost(
                            CommentDataClass(
                                commentId = UUID.randomUUID().toString(),
                                parentId  = parentId,
                                commentContent = content.value,
                                commentCreatedAt = LocalDateTime.now().toString(),
                                commentAuthor = UserDataClass(
                                    userId = Firebase.auth.currentUser?.uid,
                                    userName = Firebase.auth.currentUser?.displayName,
                                    profilePicture = Firebase.auth.currentUser?.photoUrl.toString(),
                                    accountType = "author",
                                    email = Firebase.auth.currentUser?.email
                                ),
                                imageUri = selectedImageUri.value ?: Uri.EMPTY
                            )
                        )
                        selectedImageUri.value = null
                        content.value = ""
                    },
                    colors = CardDefaults.cardColors(CoinvestDarkPurple),
                    shape = RoundedCornerShape(50.dp)
                ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text(
                            text = "Post", fontSize = 14.sp,
                            color = CoinvestBase
                        )
                    }
                }
            }
        }
    )
}