package com.generation.car.booking.booking.repository

import com.generation.car.booking.booking.data.CarBookingResponse
import com.generation.car.booking.network.RestService
import com.generation.car.booking.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CarRepositoryImp(private val restService: RestService) : CarRepository {
    override fun getAllCars(): Flow<Response<CarBookingResponse>> = flow {
        try {
            emit(Response.Loading)
            val responseApi = restService.getSearchCar()
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}