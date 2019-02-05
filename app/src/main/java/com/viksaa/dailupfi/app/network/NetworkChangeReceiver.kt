package com.viksaa.dailupfi.app.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_INTENT
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_STATE
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates
import com.viksaa.dailupfi.app.model.DailupfiSoundHandler

class NetworkChangeReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == DAILUPFI_NETWORK_INTENT) {
            val dailupfiNetworkState: DailupfiNetworkStates = intent.extras?.getSerializable(DAILUPFI_NETWORK_STATE) as DailupfiNetworkStates
            val dailupfiSoundHandler: DailupfiSoundHandler = context as DailupfiSoundHandler
            if (dailupfiNetworkState == DailupfiNetworkStates.AVAILABLE) {
                dailupfiSoundHandler.playDailupSound()
            } else {
                dailupfiSoundHandler.stopDailupSound()
            }
        }
    }
}