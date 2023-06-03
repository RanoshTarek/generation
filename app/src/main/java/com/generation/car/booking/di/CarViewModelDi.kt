package com.generation.car.booking.di

import com.generation.car.booking.booking.repository.CarRepository
import com.generation.car.booking.booking.repository.CarRepositoryImp
import com.generation.car.booking.booking.viewmodel.CarViewModel
import org.koin.dsl.module


val carModule = module {
    single<CarRepository> { CarRepositoryImp(get()) }
    single { CarViewModel(get()) }
}
