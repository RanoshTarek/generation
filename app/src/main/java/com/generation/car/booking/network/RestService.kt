package com.generation.car.booking.network

import com.generation.car.booking.booking.data.CarBookingResponse
import retrofit2.http.GET

interface RestService {

    @GET(GET_SEARCH_CAR)
    suspend fun getSearchCar(): CarBookingResponse

    companion object {
        const val GET_SEARCH_CAR = "Search/car/booking"
    }
}