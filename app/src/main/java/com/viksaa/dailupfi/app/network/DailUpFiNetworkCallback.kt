package com.viksaa.dailupfi.app.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates

class DailUpFiNetworkCallback(val context: Context) : ConnectivityManager.NetworkCallback() {

    private var startMonitoring = 0

    override fun onAvailable(network: Network) {
        logD("onAvailable() | isConnected = ${context.isConnected()}; state = ${context.getCurrentNetworkState()} startMonitoring = $startMonitoring")
        if (startMonitoring == 0) {
            startMonitoring++
        } else {
            if (context.isConnectedToCellular() || context.isConnectedToWiFi()) {
                createDailupfiNetworkIntent(DailupfiNetworkStates.AVAILABLE).also { context.sendBroadcast(it) }
            }
        }
    }

    override fun onUnavailable() {
        super.onUnavailable()
        logD("onUnavailable() | isConnected = ${context.isConnected()}; state = ${context.getCurrentNetworkState()}")
        createDailupfiNetworkIntent(DailupfiNetworkStates.UNAVAILABLE).also { context.sendBroadcast(it) }
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        logD("onLost() | isConnected = ${context.isConnected()}; state = ${context.getCurrentNetworkState()}")
        createDailupfiNetworkIntent(DailupfiNetworkStates.LOST).also { context.sendBroadcast(it) }
    }


}