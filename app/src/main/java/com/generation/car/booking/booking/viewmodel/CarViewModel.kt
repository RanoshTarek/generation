package com.generation.car.booking.booking.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.generation.car.booking.booking.data.Car
import com.generation.car.booking.booking.repository.CarRepository
import com.generation.car.booking.booking.data.CarBookingResponse
import com.generation.car.booking.utils.Response
import kotlinx.coroutines.launch


class CarViewModel(private val carRepository: CarRepository) :
    ViewModel() {
    private val _carsState = mutableStateOf<Response<CarBookingResponse>>(Response.Success(null))
    val carsState: State<Response<CarBookingResponse>> = _carsState

    fun getAllCar(carBrand: String? = null, carPrice: Double? = null) {
        viewModelScope.launch {
            carRepository.getAllCars().collect { response ->
                if (carBrand != null && response is Response.Success) {
                    response.data?.cars = response.data?.cars?.filter {
                        it.brand?.trim()?.contains(carBrand) == true
                    } as ArrayList<Car>
                }
                if (carPrice != null && response is Response.Success) {
                    response.data?.cars = response.data?.cars?.filter {
                        it.unitPrice == carPrice
                    } as ArrayList<Car>
                }


                _carsState.value = response
            }
        }
    }

}