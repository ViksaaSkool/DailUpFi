package com.viksaa.dailupfi.app.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.viksaa.dailupfi.app.extensions.createDailupfiNetworkIntent
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates

class DailUpFiNetworkCallback(val context: Context) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        logD("onAvailable() ")
        createDailupfiNetworkIntent(DailupfiNetworkStates.AVAILABLE).also { context.sendBroadcast(it) }
    }

    override fun onUnavailable() {
        super.onUnavailable()
        logD("onUnavailable() ")
        createDailupfiNetworkIntent(DailupfiNetworkStates.UNAVAILABLE).also { context.sendBroadcast(it) }
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        logD("onLost() ")
        createDailupfiNetworkIntent(DailupfiNetworkStates.LOST).also { context.sendBroadcast(it) }
    }

}