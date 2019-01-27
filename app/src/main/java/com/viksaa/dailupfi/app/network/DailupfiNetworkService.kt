package com.viksaa.dailupfi.app.network

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.viksaa.dailupfi.app.extensions.createChangeConnectivityMonitor
import com.viksaa.dailupfi.app.extensions.log
import com.viksaa.dailupfi.app.extensions.removeChangeConnectivityMonitor

class DailupfiNetworkService : Service() {

    private lateinit var dailUpFiNetworkCallback: DailUpFiNetworkCallback


    override fun onBind(intent: Intent?): IBinder? {
        log(this, "onBind()")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onCreate() {
        super.onCreate()

        createChangeConnectivityMonitor(dailUpFiNetworkCallback)
        log(this, "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log(this, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)

    }


    override fun onDestroy() {
        super.onDestroy()
        log(this, "onDestroy()")
        removeChangeConnectivityMonitor(dailUpFiNetworkCallback)
    }
}