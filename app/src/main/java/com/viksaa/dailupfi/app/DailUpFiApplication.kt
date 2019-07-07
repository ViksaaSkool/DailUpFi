package com.viksaa.dailupfi.app

import android.app.Application
import com.viksaa.dailupfi.app.extensions.isDailUpFiOn
import com.viksaa.dailupfi.app.extensions.startService
import com.viksaa.dailupfi.app.network.DailUpFiNetworkReceiver
import com.viksaa.dailupfi.app.network.DailUpFiNetworkService

class DailUpFiApplication : Application() {

    private lateinit var dailUpFiNetworkReceiver: DailUpFiNetworkReceiver
    private fun isDailUpFiNetworkReceiver() = ::dailUpFiNetworkReceiver.isInitialized

    override fun onCreate() {
        super.onCreate()
        if (isDailUpFiOn()) {
            dailUpFiNetworkReceiver = DailUpFiNetworkReceiver()
            startService<DailUpFiNetworkService>()
        }
    }

    fun getDailUpFiNetworkReceiver(): DailUpFiNetworkReceiver {
        return if (isDailUpFiNetworkReceiver()) {
            dailUpFiNetworkReceiver
        } else {
            DailUpFiNetworkReceiver()
        }

    }
}