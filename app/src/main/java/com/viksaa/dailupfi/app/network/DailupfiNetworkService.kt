package com.viksaa.dailupfi.app.network

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.createChangeConnectivityMonitor
import com.viksaa.dailupfi.app.extensions.destroy
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.extensions.removeChangeConnectivityMonitor
import com.viksaa.dailupfi.app.model.DailupfiSoundHandler

class DailupfiNetworkService : Service(), DailupfiSoundHandler {


    private lateinit var dailUpFiNetworkCallback: DailUpFiNetworkCallback
    private lateinit var dailupFiMediaPlayer: MediaPlayer
    private var binder: DailupfiBinder = DailupfiBinder()


    override fun onBind(intent: Intent?): IBinder? {
        logD("onBind()")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        logD("onCreate()")
        dailupFiMediaPlayer = MediaPlayer.create(this, R.raw.dail_up_modem)
        dailUpFiNetworkCallback = DailUpFiNetworkCallback(this, this as DailupfiSoundHandler)
        createChangeConnectivityMonitor(dailUpFiNetworkCallback)

    }

    override fun playDailupSound() {
        logD("playDailupSound()")
        if (!dailupFiMediaPlayer.isPlaying) {
            dailupFiMediaPlayer.prepare()
            dailupFiMediaPlayer.setOnPreparedListener { it ->
                it.start()
            }
        }

    }

    override fun stopDailupSound() {
        logD("stopDailupSound()")
        if (dailupFiMediaPlayer.isPlaying) {
            dailupFiMediaPlayer.stop()
            dailupFiMediaPlayer.seekTo(0)
        }
    }


    override fun onUnbind(intent: Intent?): Boolean {
        logD("onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("onDestroy()")
        removeChangeConnectivityMonitor(dailUpFiNetworkCallback)
        dailupFiMediaPlayer.destroy()
    }


    inner class DailupfiBinder : Binder() {
        internal val service: DailupfiNetworkService
            get() = this@DailupfiNetworkService
    }
}