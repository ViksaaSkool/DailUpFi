package com.viksaa.dailupfi.app.extensions

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.FileDescriptor


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
    startService(Intent(this, T::class.java).also {
        if (bundle != null) it.putExtras(bundle)
    })
}

/**
 * Start Service from Fragment, with or without bundle
 */
inline fun <reified T : Service> Fragment.startService(bundle: Bundle? = null) {
    context?.startService<T>(bundle)
}


fun Context.getFileDescriptor(filePath: String): FileDescriptor {
    return this.assets.openFd(filePath).fileDescriptor
}