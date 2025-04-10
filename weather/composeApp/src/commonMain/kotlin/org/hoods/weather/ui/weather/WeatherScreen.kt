package org.hoods.weather.ui.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import org.hoods.weather.ui.components.FlagImage
import org.hoods.weather.ui.components.LineGraph
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    weatherModelView: WeatherViewModel = koinViewModel(),
    onSearchClick:()->Unit
) {
    val weatherState by weatherModelView.weatherState.collectAsStateWithLifecycle()
    LaunchedEffect(weatherModelView.weatherState.value.selectedLocation) {
        weatherModelView.fetchWeatherData()
    }

    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if(weatherState.error != null) {
                Text(weatherState.error ?: "Unknown Error")
            }
            when(weatherState.isLoading) {
                true -> CircularProgressIndicator()
                else ->{
                    LazyColumn (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ){
                        item {
                               weatherState.weather?.let{
                                   CurrentWeather(currentWeather = it.currentWeather)


                            weatherState.dailyWeatherInfo?.let{
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
                                ){
                                    SunSetWeather(weatherInfo = it)
                                    Spacer(Modifier.weight(1f))
                                    UvIndexWeather(weatherInfo = it)
                                }
                            }
                            Spacer(Modifier.height(16.dp))
                            HourlyWeatherCard(
                                hourly = it.hourly
                            )
                               }
                        }
                        item {
                            weatherState.weather?.let{ weather ->
                            LineGraph(
                                dataPoints = weather.hourly.weatherInfo,
                                xValueMapper = { it.time.take(2) },
                                yValueMapper = { it.temperature.toFloat() },
                                modifier = Modifier.fillMaxWidth()
                                    .height(250.dp),
                                graphTitle = "Temperature Over Time",
                            )
                        }
                        }

                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.TopEnd)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = ripple(),
                    onClick = onSearchClick
                )
        ){
            weatherState.selectedLocation?.let{
                val imageRequest = ImageRequest.Builder(context = LocalPlatformContext.current)
                    .data(it.flagUrl)
                    .build()
                FlagImage(imageRequest = imageRequest,modifier= Modifier.size(24.dp))
                Spacer(Modifier.width(4.dp))
                Text(text = it.name,color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

    }


}