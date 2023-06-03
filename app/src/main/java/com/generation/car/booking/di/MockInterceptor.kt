package com.generation.car.booking.di

import com.generation.car.booking.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("booking") -> getListOfReposBeingStarredJson
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()

    }

}

const val getListOfReposBeingStarredJson = """
{
  "status" : {
    "code": 200,
    "message": "success"
  },
  "cars": [
    {
      "model": 2021,
      "plate_number": "ABC 123",
      "brand": "Honda",
      "unit_price": 30.5,
      "currency": "EGP",
      "color": "RED"
    },
    {
      "model": 2020,
      "plate_number": "EFG 321",
      "brand": "Hyundai",
      "unit_price": 50.5,
      "currency": "EGP",
      "color":"BLUE"
    },
    {
      "model": 2021,
      "plate_number": "XYZ 313",
      "brand": "MG",
      "unit_price": 15.5,
      "currency": "EGP",
      "color": "BLACK"
    },
    {
      "model": 2022,
      "plate_number": "ABC 123",
      "brand": "Opel",
      "unit_price": 70.5,
      "currency": "EGP",
      "color": "WHITE"
    }
  ]
}
"""