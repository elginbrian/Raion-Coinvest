package com.raion.coinvest.presentation.widget.shareButton

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat.startActivity
import com.raion.coinvest.R

@Composable
fun ShareButton(
    header: String = "POST",
    content: String,
    owner: String,
    date: String
){
    val context = LocalContext.current
    val formatText = "$header \n$owner \n$date \n$content"
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, formatText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    Icon(painter = painterResource(id = R.drawable.share_icon), contentDescription = "Share", modifier = Modifier.scale(0.7f).clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        startActivity(context, shareIntent, null)
    })
}