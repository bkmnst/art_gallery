package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtGallery()
                }
            }
        }
    }
}

@Composable
fun ArtGallery() {
    var imageID by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ImageSpotlight(imageID = imageID)
        ImageDescription(imageID = imageID)
        ImageNavigationButtons(onPrevious = {
            imageID--
            if (imageID < 1) imageID = 3
        }) {
            imageID++
            if (imageID > 3) imageID = 1
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}

@Composable
fun ImageSpotlight(imageID: Int) {
    val imageResource = when (imageID) {
        1 -> R.drawable.butterfly
        2 -> R.drawable.homura
        3 -> R.drawable.mery
        else -> R.drawable.butterfly
    }
    Box(
        modifier = Modifier
            .padding(40.dp)
            .clip(RoundedCornerShape(15.dp))
            .animateContentSize()
            .height(500.dp)
    ) {
        Image(
            painter = painterResource(id = imageResource), contentDescription = "Cute!"
        )
    }
}

@Composable
fun ImageDescription(imageID: Int) {
    val nameResource = when (imageID) {
        1 -> "Art 1"
        2 -> "Art 2"
        3 -> "Art 3"
        else -> "Art 1"
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Text(
            text = nameResource,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Light
        )
        Row {
            Text(
                text = "Matcha ",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "(2023)",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.ExtraLight
            )
        }
    }
}

@Composable
fun ImageNavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = onPrevious, modifier = Modifier.weight(2f)
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNext, modifier = Modifier.weight(2f)
        ) {
            Text(text = "Next")
        }
    }
}