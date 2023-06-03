package com.generation.car.booking.booking.repository

import com.generation.car.booking.booking.data.CarBookingResponse
import com.generation.car.booking.utils.Response
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getAllCars(): Flow<Response<CarBookingResponse>>
}
