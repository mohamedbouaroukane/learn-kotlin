package org.hoods.weather.ui.weather

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DetailedInformationCard(
    title:String,
    value:String,
    footer:String
){
    Card(
        modifier = Modifier.padding(horizontal = 8.dp),
    ){
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.basicMarquee()
            )
            Text(
                text = value,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.basicMarquee()
            )
            Text(
                text = footer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.basicMarquee()
            )
        }
    }
}