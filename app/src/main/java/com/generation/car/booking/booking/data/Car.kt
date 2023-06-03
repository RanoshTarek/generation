package com.generation.car.booking.booking.data

import com.google.gson.annotations.SerializedName


data class Car(
    @SerializedName("model") var model: Int? = null,
    @SerializedName("plate_number") var plateNumber: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("unit_price") var unitPrice: Double? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("color") var color: String? = null
)