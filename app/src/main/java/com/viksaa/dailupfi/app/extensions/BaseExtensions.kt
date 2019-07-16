package com.viksaa.dailupfi.app.extensions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker


/**
 * If boolean var of interest is true return value after then
 */
infix fun <T> Boolean.then(value: T?) = if (this) value else null


inline fun <T> Boolean.then(function: () -> T, default: () -> T) = if (this) function() else default()

infix inline fun <reified T> Boolean.then(function: () -> T) = if (this) function() else null

infix inline fun <reified T, reified Type> Type?.then(callback: (Type) -> T) = if (this != null) callback(this) else null


fun Context.inflater() = LayoutInflater.from(this)

fun Context.getCompatDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun Context.getCompatColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Context.getDimension(@DimenRes id: Int) = resources.getDimension(id)

fun Context.getDimensionPixelOffset(@DimenRes id: Int) = resources.getDimensionPixelOffset(id)

fun Context.getDimensionPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

fun Context.isLandscape() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.isPortrait() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT


/**
 * Start Service from Context, with or without bundle
 */
inline fun <reified T : Service> Context.startService(bundle: Bundle? = null) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        startForegroundService(Intent(this, T::class.java).also {
            if (bundle != null) {
                it.putExtras(bundle)
            }
        })
    } else {
        startService(Intent(this, T::class.java).also {
            if (bundle != null) {
                it.putExtras(bundle)
            }
        })
    }
}

/**
 *  Handling Android 9+ service start - has to be a foregorund one
 */
fun Service.startDefaultForegroundService() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val CHANNEL_ID = "default_channel"
        val channel = NotificationChannel(CHANNEL_ID,
                " ",
                NotificationManager.IMPORTANCE_MIN)

        (getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build()

        startForeground(1, notification)
    }
}

/**
 *
 * Handling Android 9+ service stop - has to be a foregorund one
 */
fun Service.stopForegorundService(stopIntent: Intent) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P &&
            stopIntent.action == DAILUPFI_ACTION_STOP_SERVICE) {
        stopForeground(true)
        stopSelf()
    }
}


/**
 * Start Service from Fragment, with or without bundle
 */
inline fun <reified T : Service> Fragment.startService(bundle: Bundle? = null) {
    context?.startService<T>(bundle)
}


/**
 * Stop Service from Context, with or without bundle
 */
inline fun <reified T : Service> Context.stopService(bundle: Bundle? = null) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        startService(Intent(this, T::class.java)
                .apply {
                    action = DAILUPFI_ACTION_STOP_SERVICE
                })

    } else {
        stopService(Intent(this, T::class.java).also {
            if (bundle != null) {
                it.putExtras(bundle)
            }
        })
    }


}

/**
 * Stop Service from Fragment, with or without bundle
 */
inline fun <reified T : Service> Fragment.stopService(bundle: Bundle? = null) {
    context?.stopService<T>(bundle)
}

inline fun <reified T : Worker> Context.startOneTimeWorkRequest() {
    WorkManager.getInstance().enqueue(OneTimeWorkRequest.Builder(T::class.java)
            .build())

}

fun MediaPlayer.destroy() {
    this.setOnPreparedListener(null)
    this.release()
}

fun MediaPlayer.play() {
    if (!this.isPlaying) {
        this.start()
    }
}

fun MediaPlayer.stopAndReset() {
    if (this.isPlaying) {
        this.stop()
        this.seekTo(0)
    }
}




