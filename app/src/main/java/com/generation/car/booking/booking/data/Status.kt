package com.generation.car.booking.booking.data

import com.google.gson.annotations.SerializedName


data class Status(
    @SerializedName("code") var code: Int? = null,
    @SerializedName("message") var message: String? = null
)