package com.generation.car.booking.booking.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize



data class Car(
    @SerializedName("model") var model: Int? = null,
    @SerializedName("plate_number") var plateNumber: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("unit_price") var unitPrice: Double? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("color") var color: String? = null
)