package com.viksaa.dailupfi.app.extensions

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates


/** CONSTANTS **/
const val DAILUPFI_NETWORK_INTENT: String = "dailupfi_network_intent"
const val DAILUPFI_NETWORK_STATE: String = "dailupfi_network_state"


/** EXTENSION METHODS **/

fun Context.getConnectivityManager(): ConnectivityManager = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

fun Context.getNetworkInfo(): NetworkInfo = getConnectivityManager().activeNetworkInfo

fun Context.hasActiveNetworkInfo(): Boolean = getConnectivityManager().activeNetworkInfo != null

fun Context.isConnected(): Boolean = hasActiveNetworkInfo() && getNetworkInfo().isConnected

fun Context.isConnecting(): Boolean = hasActiveNetworkInfo() && getNetworkInfo().detailedState == NetworkInfo.DetailedState.CONNECTING

fun Context.isConnectedToWiFi(): Boolean {
    return isConnected() && if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val capabilities = getConnectivityManager().getNetworkCapabilities(getConnectivityManager().activeNetwork)
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    } else {
        getNetworkInfo().subtype == ConnectivityManager.TYPE_WIFI
    }
}

/**
 * Get the current state of the active network - logging purposes
 */
fun Context.getCurrentNetworkState(): String {
    return if (hasActiveNetworkInfo()) {
        getNetworkInfo().detailedState.name
    } else {
        ""
    }
}


fun Context.isConnectedToCellular(): Boolean {
    return isConnected() && if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val capabilities = getConnectivityManager().getNetworkCapabilities(getConnectivityManager().activeNetwork)
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    } else {
        getNetworkInfo().subtype == ConnectivityManager.TYPE_MOBILE
    }
}


fun Context.createChangeConnectivityMonitor(networkCallback: ConnectivityManager.NetworkCallback) {
    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
}


fun Context.removeChangeConnectivityMonitor(networkCallback: ConnectivityManager.NetworkCallback) {
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .unregisterNetworkCallback(networkCallback)
}


/** Helper methods **/

fun createDailupfiNetworkIntent(dailupfiState: DailupfiNetworkStates): Intent = Intent().apply {
    action = DAILUPFI_NETWORK_INTENT
    logD("createDailupfiNetworkIntent() | dailupfiState = ${dailupfiState.state}")
    putExtra(DAILUPFI_NETWORK_STATE, dailupfiState)
}

fun createDailupfiNetworkIntentFilter(): IntentFilter = IntentFilter().apply { addAction(DAILUPFI_NETWORK_INTENT) }



