package com.generation.car.booking.booking.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.generation.car.booking.booking.repository.CarRepository
import com.generation.car.booking.booking.data.CarBookingResponse
import com.generation.car.booking.utils.Response
import kotlinx.coroutines.launch


class CarViewModel( private val carRepository: CarRepository) :
    ViewModel() {
    private val _carsState = mutableStateOf<Response<CarBookingResponse>>(Response.Success(null))
    val carsState: State<Response<CarBookingResponse>> = _carsState

    fun getAllCar() {
        viewModelScope.launch {
            carRepository.getAllCars().collect { response ->
                _carsState.value = response
            }
        }
    }

}