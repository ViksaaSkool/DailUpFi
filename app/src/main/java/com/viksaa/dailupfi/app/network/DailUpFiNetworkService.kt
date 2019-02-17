package com.viksaa.dailupfi.app.network

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.model.DailUpFiSoundListener

class DailUpFiNetworkService : Service(), DailUpFiSoundListener {


    private lateinit var mDailUpFiNetworkCallback: DailUpFiNetworkCallback
    private lateinit var dailupFiMediaPlayer: MediaPlayer
    private lateinit var mDailUpFiNetworkReceiver: DailUpFiNetworkReceiver
    private var binder: DailupfiBinder = DailupfiBinder()


    override fun onBind(intent: Intent?): IBinder? {
        logD("onBind()")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        logD("onCreate()")
        dailupFiMediaPlayer = MediaPlayer.create(this, R.raw.dail_up_modem)
        mDailUpFiNetworkCallback = DailUpFiNetworkCallback(this)
        mDailUpFiNetworkReceiver = DailUpFiNetworkReceiver()
        mDailUpFiNetworkReceiver.addDailupfiListner(this)
        registerReceiver(mDailUpFiNetworkReceiver, createDailupfiNetworkIntentFilter())
        createChangeConnectivityMonitor(mDailUpFiNetworkCallback)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun playDailupSound() {
        logD("playDailupSound()")
        if (!dailupFiMediaPlayer.isPlaying) {
            dailupFiMediaPlayer.start()

        }
    }


    override fun stopDailupSound() {
        logD("stopDailupSound()")
        if (dailupFiMediaPlayer.isPlaying) {
            dailupFiMediaPlayer.stop()
            dailupFiMediaPlayer.seekTo(0)
            dailupFiMediaPlayer = MediaPlayer.create(this, R.raw.dail_up_modem)
        }
    }


    override fun onUnbind(intent: Intent?): Boolean {
        logD("onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("onDestroy()")
        removeChangeConnectivityMonitor(mDailUpFiNetworkCallback)
        mDailUpFiNetworkReceiver.removeDailupfiListner(this)
        unregisterReceiver(mDailUpFiNetworkReceiver)
        dailupFiMediaPlayer.destroy()
    }


    inner class DailupfiBinder : Binder() {
        internal val service: DailUpFiNetworkService
            get() = this@DailUpFiNetworkService
    }
}

