package com.generation.car.booking

import android.app.Application
import android.content.Context
import com.generation.car.booking.di.carModule
import com.generation.car.booking.di.networkModule
import org.koin.core.context.GlobalContext.startKoin


class BaseApplication : Application() {
    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoinDI()
    }


    private fun provideComponent() = appComponent

    private fun startKoinDI() {
        startKoin {
            modules(
                provideComponent()
            )
        }

    }

    private val appComponent = listOf(networkModule, carModule)


}
