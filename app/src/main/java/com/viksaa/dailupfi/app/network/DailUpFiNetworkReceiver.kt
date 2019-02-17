package com.viksaa.dailupfi.app.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_INTENT
import com.viksaa.dailupfi.app.extensions.DAILUPFI_NETWORK_STATE
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.model.DailUpFiAnimationListener
import com.viksaa.dailupfi.app.model.DailUpFiSoundListener
import com.viksaa.dailupfi.app.model.DailupfiNetworkStates

class DailUpFiNetworkReceiver : BroadcastReceiver() {

    private var mDailUpFiSoundListener: MutableSet<DailUpFiSoundListener> = hashSetOf<DailUpFiSoundListener>()
    private var mDailUpFiAnimationListener: MutableSet<DailUpFiAnimationListener> = hashSetOf<DailUpFiAnimationListener>()

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
        if (l is DailUpFiSoundListener) {
            mDailUpFiSoundListener.add(l)
        } else if (l is DailUpFiAnimationListener) {
            mDailUpFiAnimationListener.add(l)
        }
    }

    /**
     * Remove listeners from the appropriate set
     */
    fun removeDailupfiListner(l: Any) {

        if (l is DailUpFiSoundListener) {
            mDailUpFiSoundListener.remove(l)
        } else if (l is DailUpFiAnimationListener) {
            mDailUpFiAnimationListener.remove(l)
        }
    }

    /**
     * Notify the state change in the sets of listeners - if there are any
     *
     * @param connected - flag to determine type of event
     */
    private fun notifyState(connected: Boolean) {

        mDailUpFiAnimationListener.takeIf { !it.isEmpty() }.apply {
            logD("notifyState() | dailupfiAnimationListener connected = $connected; dailupfiAnimationListener size = ${this?.size}")
            this?.forEach {
                if (connected) {
                    it.onConnecting()
                } else {
                    it.onDisconnected()
                }
            }
        }


        mDailUpFiSoundListener.takeIf { !it.isEmpty() }.apply {
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

