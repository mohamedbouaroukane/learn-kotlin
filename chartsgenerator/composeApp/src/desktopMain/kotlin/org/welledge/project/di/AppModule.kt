package org.welledge.project.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.welledge.project.data.repository.ChartRepositoryImpl
import org.welledge.project.domain.repository.ChartRepository
import org.welledge.project.domain.usecase.GenerateChartUseCase
import org.welledge.project.presentation.viewmodel.ChartViewModel

val appModule = module {
    single<ChartRepository> { ChartRepositoryImpl() }

    factory { GenerateChartUseCase(get()) }

    viewModel { ChartViewModel(get()) }
}