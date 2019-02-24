package com.viksaa.dailupfi.app

import android.app.Application
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.network.DailUpFiNetworkService

class DailUpFiApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        if (getDailUpFiPreferences().get(DAILUPFI_ON_KEY, false)) {
            logD("onCreate() | DAILUPFI_ON start service!")
            startService<DailUpFiNetworkService>()
        }
    }
}