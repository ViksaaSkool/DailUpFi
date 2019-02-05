package com.viksaa.dailupfi.app.extensions

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkRequest
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates

/** CONSTANTS **/
const val DAILUPFI_NETWORK_INTENT: String = "dailupfi_network_intent"
const val DAILUPFI_NETWORK_STATE: String = "dailupfi_network_state"


/** EXTENSION METHODS **/
fun Context.getNetworkInfo(): NetworkInfo = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.isConnected(): Boolean = this.getNetworkInfo().isConnected


fun Context.createChangeConnectivityMonitor(networkCallback: ConnectivityManager.NetworkCallback) {
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
}


fun Context.removeChangeConnectivityMonitor(networkCallback: ConnectivityManager.NetworkCallback) {
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .unregisterNetworkCallback(networkCallback)
}


/** Helper methods **/

fun createDailupfiNetworkIntent(dailupfiState: DailupfiNetworkStates): Intent = Intent().apply {
    action = DAILUPFI_NETWORK_INTENT
    putExtra(DAILUPFI_NETWORK_STATE, dailupfiState)
}

fun createDailupfiNetworkIntentFilter(): IntentFilter = IntentFilter().apply { addAction(DAILUPFI_NETWORK_INTENT) }



