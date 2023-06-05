package com.generation.car.booking

import CarDetailsFragment
import CarFragment
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.generation.car.booking.booking.data.Car
import com.generation.car.booking.navigation.Routes
import com.generation.car.booking.ui.theme.CarBookingTheme
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import kotlin.math.log


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            CarBookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ToDometerApp()
                }
            }
        }
    }

    @Composable
    fun ToDometerApp() {
        val navController = rememberNavController()
        CarBookingTheme {
            NavHost(navController = navController, startDestination = "home") {
                composable(Routes.home) {
                    CarFragment(navController)
                }
                composable(Routes.carDetails) { backStackEntry ->
                    val carJson = backStackEntry.arguments?.getString("car")
                    val carObject = Gson().fromJson(carJson, Car::class.java)
                    if (carObject != null) {
                        CarDetailsFragment(car = carObject)
                    }
                }
            }
        }

    }

}

