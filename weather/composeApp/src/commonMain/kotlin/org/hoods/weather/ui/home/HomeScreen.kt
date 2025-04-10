package org.hoods.weather.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import org.hoods.weather.ui.components.NavigationType
import org.hoods.weather.ui.components.SearchLocationContent
import org.hoods.weather.ui.weather.WeatherScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val (search,onSearchChange) = remember { mutableStateOf("") }
    val context = LocalPlatformContext.current
    var showSearchLocation by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(showSearchLocation,modifier = Modifier) {
        SearchLocationContent(
            modifier =modifier,
            state = state,
            search = search,
            onSearchChange = onSearchChange,
            onSubmit = {homeViewModel.fetchGeoLocation(search)},
            context = context,
            navigationType = navigationType,
            onFavouriteClick = {
                homeViewModel.setSelectedLocation(it)
                homeViewModel.saveFavouriteLocation()
                homeViewModel.getGeoLocation()
                showSearchLocation = !showSearchLocation
            },
            onNavigateBack = {
                showSearchLocation = false
            }

        )
    }

    AnimatedVisibility(!showSearchLocation,modifier = Modifier) {
        if(state.isLocationSelected){
            WeatherScreen(
                modifier = modifier,
                onSearchClick = {
                    showSearchLocation = !showSearchLocation
                }
            )
        }else{
            Text("Please select a location to get started")
        }
    }



}