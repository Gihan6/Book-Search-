package com.plcoding.bookpedia.book.prsentation.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.camera
import cmp_bookpedia.composeapp.generated.resources.camera_icon
import cmp_bookpedia.composeapp.generated.resources.change_password
import cmp_bookpedia.composeapp.generated.resources.edit
import cmp_bookpedia.composeapp.generated.resources.edit_profile
import cmp_bookpedia.composeapp.generated.resources.full_name
import cmp_bookpedia.composeapp.generated.resources.password
import cmp_bookpedia.composeapp.generated.resources.user
import cmp_bookpedia.composeapp.generated.resources.user_icon
import com.plcoding.bookpedia.book.prsentation.user_profile.component.PicImageFromGallery
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import com.plcoding.bookpedia.core.presentation.Orange
import com.plcoding.bookpedia.core.presentation.SandYellow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserProfileScreenRoot() {


}

@Composable
fun UserProfileScreen(modifier: Modifier = Modifier) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var onImageClick by remember { mutableStateOf(false) }




    Box(modifier.fillMaxSize().background(DesertWhite).padding(10.dp)) {
        Column(modifier = modifier.fillMaxWidth()) {
            if (onImageClick){
                PicImageFromGallery{
                    imageBitmap=it
                    onImageClick=true

                }

            }

            Spacer(modifier = Modifier.height(150.dp))
            Box(modifier = modifier.align(Alignment.CenterHorizontally)) {
                if (imageBitmap==null){
                        Image(
                        painter = painterResource(Res.drawable.user),
                        contentDescription = stringResource(Res.string.user_icon),
                        modifier = modifier.align(Alignment.Center).size(140.dp).padding(7.dp)
                    )

                }else{
                    Image(
                        bitmap = imageBitmap!!,
                        contentDescription = "Profile",
                        modifier = Modifier.size(140.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    onImageClick=false
                }

                IconButton(
                    modifier = modifier.align(Alignment.BottomEnd).size(60.dp).padding(5.dp),
                    onClick = {
                            onImageClick=true
                    }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.camera),
                        contentDescription = stringResource(Res.string.camera_icon),
                        modifier = Modifier.size(120.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Gihan Abd elnaser",
                color = DarkBlue,
                style = TextStyle(
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold

                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp)
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = SandYellow,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .background(
                        color = SandYellow.copy(.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "gihan@gmail.com",
                    style = TextStyle(
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Orange
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )


                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Row(modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = stringResource(Res.string.full_name),
                        modifier = modifier.size(20.dp)
                    )
                    Text(
                        text = stringResource(Res.string.edit_profile),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp),
                        color = DarkBlue
                    )
                }
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .height(1.dp)
                        .background(Color.LightGray)
                )
                Row(modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(Res.drawable.password),
                        contentDescription = stringResource(Res.string.change_password),
                        modifier = modifier.size(20.dp)
                    )
                    Text(
                        text = stringResource(Res.string.change_password),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp),
                        color = DarkBlue
                    )
                }

            }


        }

    }
}