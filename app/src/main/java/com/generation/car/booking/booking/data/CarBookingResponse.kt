package com.generation.car.booking.booking.data

import com.google.gson.annotations.SerializedName


data class CarBookingResponse(
    @SerializedName("status") var status: Status? = Status(),
    @SerializedName("cars") var cars: ArrayList<Car> = arrayListOf()
)