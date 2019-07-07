package com.viksaa.dailupfi.app.network

import android.net.ConnectivityManager
import android.net.Network
import com.viksaa.dailupfi.app.extensions.logD

abstract class MonitoringNetwork : ConnectivityManager.NetworkCallback() {

    private var startMonitoring = 0

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        logD("onAvailable() | startMonitoring = $startMonitoring")
        if (startMonitoring == 0) {
            startMonitoring++
        } else {
            onAvailable()
        }
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        logD("onLost() | startMonitoring = $startMonitoring")
        if (startMonitoring == 0) {
            startMonitoring++
        } else {
            onLost()
        }
    }

    override fun onUnavailable() {
        logD("onUnavailable() | startMonitoring = $startMonitoring")
        super.onUnavailable()
        if (startMonitoring == 0) {
            startMonitoring++
        } else {
            onUnAvailable()
        }
    }

    abstract fun onAvailable()
    abstract fun onLost()
    abstract fun onUnAvailable()
}