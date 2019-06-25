package com.viksaa.dailupfi.app

import android.app.Application
import com.viksaa.dailupfi.app.extensions.isDailUpFiOn
import com.viksaa.dailupfi.app.extensions.startService
import com.viksaa.dailupfi.app.network.DailUpFiNetworkService

class DailUpFiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isDailUpFiOn()) {
            startService<DailUpFiNetworkService>()
        }
    }
}