package com.viksaa.dailupfi.app

import android.app.Application
import com.viksaa.dailupfi.app.extensions.startService
import com.viksaa.dailupfi.app.network.DailupfiNetworkService

class DailUpFiApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startService<DailupfiNetworkService>()
    }
}