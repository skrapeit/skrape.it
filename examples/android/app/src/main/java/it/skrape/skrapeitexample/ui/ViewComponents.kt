package it.skrape.skrapeitexample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import it.skrape.skrapeitexample.R
import it.skrape.skrapeitexample.User

@Composable
internal fun SkrapeItLogo() {
    Image(
        modifier = Modifier.size(150.dp),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
    )
}


@Composable
private fun Tile(content: @Composable () -> Unit) {
    Surface(
        color = Color(0xFFfeefbc),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        content = content
    )
}

@Composable
internal fun StargazerTile(data: User) {
    Tile {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val painter = rememberCoilPainter(
                request = data.imageUrl,
                requestBuilder = {
                    transformations(CircleCropTransformation())
                },
                fadeIn = true
            )
            val linearGradientBrush = Brush.linearGradient(
                colors = listOf(Color(0xFFcae7f7), Color(0xFFeeb8e1))
            )
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .border(10.dp, linearGradientBrush, CircleShape),
                painter = painter,
                contentDescription = ""
            )
            if (painter.loadState is ImageLoadState.Loading) {
                CircularProgressIndicator()
            }

            Text(
                text = data.name,
                style = MaterialTheme.typography.h6,
                color = Color(0xFF6a4e3b),
            )

        }
    }
}

@Composable
internal fun HintText() {
    Tile {
        Text(
            text = """
                This is an "how to use skrape{it} on Android" show-case.
                By clicking the button the latest github users that have given skrape{it} a star will be scraped from the github website and displayed.
            """.trimIndent(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
    }
}