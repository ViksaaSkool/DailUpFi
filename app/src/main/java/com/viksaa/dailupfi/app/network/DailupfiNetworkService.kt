package com.viksaa.dailupfi.app.network

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.viksaa.dailupfi.app.extensions.createChangeConnectivityMonitor
import com.viksaa.dailupfi.app.extensions.log
import com.viksaa.dailupfi.app.extensions.removeChangeConnectivityMonitor

class DailupfiNetworkService : Service() {

    private lateinit var dailUpFiNetworkCallback: DailUpFiNetworkCallback
    private lateinit var dailupFiMediaPlayer: MediaPlayer


    override fun onBind(intent: Intent?): IBinder? {
        log("onBind()")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        dailUpFiNetworkCallback = DailUpFiNetworkCallback(this)
        createChangeConnectivityMonitor(dailUpFiNetworkCallback)
        log("onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand()")
        return super.onStartCommand(intent, flags, startId)

    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy()")
        removeChangeConnectivityMonitor(dailUpFiNetworkCallback)
    }
}