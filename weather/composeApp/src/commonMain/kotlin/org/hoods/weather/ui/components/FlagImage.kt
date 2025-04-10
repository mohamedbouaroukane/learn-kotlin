package org.hoods.weather.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun FlagImage (
            modifier: Modifier = Modifier.size(40.dp),
    imageRequest: ImageRequest
){
    AsyncImage(
        model = imageRequest,
        modifier = modifier.clip(CircleShape),
        contentDescription = null,
        contentScale = ContentScale.Crop

    )
}