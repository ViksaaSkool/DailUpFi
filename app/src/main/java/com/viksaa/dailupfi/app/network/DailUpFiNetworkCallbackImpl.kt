package com.viksaa.dailupfi.app.network

import android.content.Context
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.network.listeners.DailupfiNetworkStates

class DailUpFiNetworkCallbackImpl(val context: Context) : MonitoringNetwork() {

    override fun onAvailable() {
        logD("onAvailable() | isConnected = ${context.isConnected()}; " +
                "state = ${context.getCurrentNetworkState()} ")
        if (context.isConnectedToCellular() || context.isConnectedToWiFi()) {
            createDailupfiNetworkIntent(DailupfiNetworkStates.AVAILABLE).also { context.sendBroadcast(it) }
        }
    }

    override fun onLost() {
        logD("onLost() | isConnected = ${context.isConnected()}; state = ${context.getCurrentNetworkState()}")
        createDailupfiNetworkIntent(DailupfiNetworkStates.LOST).also { context.sendBroadcast(it) }
    }

    override fun onUnAvailable() {
        logD("onUnavailable() | isConnected = ${context.isConnected()}; state = ${context.getCurrentNetworkState()}")
        createDailupfiNetworkIntent(DailupfiNetworkStates.UNAVAILABLE).also { context.sendBroadcast(it) }
    }

}