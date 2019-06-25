package com.viksaa.dailupfi.app.network

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.model.DailUpFiSoundListener


class DailUpFiNetworkService : Service(), DailUpFiSoundListener {


    private lateinit var dailUpFiNetworkCallback: DailUpFiNetworkCallback
    private lateinit var dailUpFiMediaPlayer: MediaPlayer
    private lateinit var dailUpFiNetworkReceiver: DailUpFiNetworkReceiver
    private var binder: DailupfiBinder = DailupfiBinder()


    override fun onBind(intent: Intent?): IBinder? {
        logD("onBind()")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        logD("onCreate() | Version: ${Build.VERSION.SDK_INT}")
        //Android 9+
        startDefaultForegroundService()
        dailUpFiMediaPlayer = MediaPlayer.create(this, com.viksaa.dailupfi.app.R.raw.dail_up_modem)
        dailUpFiNetworkCallback = DailUpFiNetworkCallback(this)
        dailUpFiNetworkReceiver = DailUpFiNetworkReceiver()
        dailUpFiNetworkReceiver.addDailupfiListner(this)
        registerReceiver(dailUpFiNetworkReceiver, createDailupfiNetworkIntentFilter())
        createChangeConnectivityMonitor(dailUpFiNetworkCallback)


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun playDailupSound() {
        logD("playDailupSound()")
        if (isDailUpFiOn()) {
            dailUpFiMediaPlayer.play()
        }
    }

    override fun stopDailupSound() {
        logD("stopDailupSound()")
        if (dailUpFiMediaPlayer.isPlaying) {
            dailUpFiMediaPlayer.stop()
            dailUpFiMediaPlayer.seekTo(0)
            dailUpFiMediaPlayer = MediaPlayer.create(this, com.viksaa.dailupfi.app.R.raw.dail_up_modem)
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
        dailUpFiNetworkReceiver.removeDailupfiListner(this)
        unregisterReceiver(dailUpFiNetworkReceiver)
        dailUpFiMediaPlayer.destroy()
    }


    inner class DailupfiBinder : Binder() {
        internal val service: DailUpFiNetworkService
            get() = this@DailUpFiNetworkService
    }
}

