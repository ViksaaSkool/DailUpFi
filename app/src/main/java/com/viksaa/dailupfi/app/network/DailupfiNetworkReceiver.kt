package com.viksaa.dailupfi.app.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_INTENT
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_STATE
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.model.DailupfiAnimationListener
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates
import com.viksaa.dailupfi.app.model.DailupfiSoundListener

class DailupfiNetworkReceiver : BroadcastReceiver() {

    private var dailupfiSoundListener: MutableSet<DailupfiSoundListener> = hashSetOf<DailupfiSoundListener>()
    private var dailupfiAnimationListener: MutableSet<DailupfiAnimationListener> = hashSetOf<DailupfiAnimationListener>()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == DAILUPFI_NETWORK_INTENT) {
            val dailupfiNetworkState: DailupfiNetworkStates = intent.extras?.getSerializable(DAILUPFI_NETWORK_STATE) as DailupfiNetworkStates
            logD("onReceive() | dailupfiNetworkState = ${dailupfiNetworkState.state}")
            if (dailupfiNetworkState == DailupfiNetworkStates.AVAILABLE) {
                notifyState(true)
            } else {
                notifyState(false)
            }
        }
    }

    /**
     * Add listeners to the appropriate set
     */
    fun addDailupfiListner(l: Any) {
        if (l is DailupfiSoundListener) {
            dailupfiSoundListener.add(l)
        } else if (l is DailupfiAnimationListener) {
            dailupfiAnimationListener.add(l)
        }
    }

    /**
     * Remove listeners from the appropriate set
     */
    fun removeDailupfiListner(l: Any) {

        if (l is DailupfiSoundListener) {
            dailupfiSoundListener.remove(l)
        } else if (l is DailupfiAnimationListener) {
            dailupfiAnimationListener.remove(l)
        }
    }

    /**
     * Notify the state change in the sets of listeners - if there are any
     *
     * @param connected - flag to determine type of event
     */
    private fun notifyState(connected: Boolean) {

        dailupfiAnimationListener.takeIf { !it.isEmpty() }.apply {
            logD("notifyState() | dailupfiAnimationListener connected = $connected; dailupfiAnimationListener size = ${this?.size}")
            this?.forEach {
                if (connected) {
                    it.onConnecting()
                } else {
                    it.onDisconnected()
                }
            }
        }


        dailupfiSoundListener.takeIf { !it.isEmpty() }.apply {
            logD("notifyState() | dailupfiSoundListener connected = $connected; dailupfiSoundListener size = ${this?.size}")
            this?.forEach {
                if (connected) {
                    it.playDailupSound()
                } else {
                    it.stopDailupSound()
                }
            }
        }
    }

}

