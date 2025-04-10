package org.hoods.weather.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.request.ImageRequest
import org.hoods.weather.geo_location.domain.models.GeoLocation
import org.hoods.weather.ui.home.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationContent(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    state: HomeState,
    search: String,
    onSearchChange: (String) -> Unit,
    onFavouriteClick: (GeoLocation)->Unit,
    onSubmit: () -> Unit,
    context: PlatformContext,
    onNavigateBack: () -> Unit
) {
    var expanded by rememberSaveable {mutableStateOf<Boolean>(false)}
    var padding = if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER){
        200.dp
    }else{
        0.dp
    }

    Box(modifier = modifier.fillMaxWidth().padding(horizontal = padding),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            inputField = {
            Row{
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back",
                        )
                    }
                SearchBarDefaults.InputField(
                    query = search,
                    onQueryChange = onSearchChange,
                    onSearch = {
                        onSubmit()
                    },
                    expanded = expanded,
                    onExpandedChange = {expanded = it},
                    placeholder = { Text("Hinted search text") },
                    leadingIcon = {Icon(Icons.Default.Search, contentDescription = null)},
                    trailingIcon = {Icon(Icons.Default.MoreVert, contentDescription = null)}
                )
            }
            },
            expanded = expanded,
            onExpandedChange = {expanded = it},
        ){
            Card{
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AnimatedVisibility(state.error != null) {
                        Text(state.error ?: "Unknown Error")
                    }

                    AnimatedVisibility(state.isLoading ) {
                        CircularProgressIndicator()
                    }
                    LazyColumn {
                       items(state.geoLocations){
                            val imageRequest = ImageRequest.Builder(context=context)
                                .data(it.flagUrl)
                                .build()
                           Row(
                               modifier = Modifier.fillMaxWidth().padding(16.dp)
                                   .background(color = if(it.id == state.selectedLocation?.id) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified),
                               horizontalArrangement = Arrangement.SpaceBetween,

                           ) {
                               Row(
                                   horizontalArrangement = Arrangement.Start,
                                   verticalAlignment = Alignment.CenterVertically,
                                   modifier = Modifier.weight(1f)
                               ){
                                   FlagImage(imageRequest=imageRequest)
                                   Spacer(Modifier.width(10.dp))
                                   Column {
                                       Text(
                                           text = "${it.name},${it.countryName},${it.countryCode}",
                                           style = MaterialTheme.typography.titleLarge
                                           )
                                       Spacer(Modifier.width(8.dp))
                                       Row{
                                           Text(
                                               text = "${it.latitude},${it.longitude}",
                                               style = MaterialTheme.typography.titleLarge
                                           )
                                       }
                                   }
                               }

                               IconButton(onClick = {onFavouriteClick(it)}){
                                   Icon(Icons.Default.Favorite, contentDescription = "Favourite icon")
                               }

                           }
                            HorizontalDivider()
                       }
                    }
                }
            }
        }

    }
}