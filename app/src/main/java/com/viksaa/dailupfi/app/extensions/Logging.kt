package com.viksaa.dailupfi.app.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.viksaa.dailupfi.app.BuildConfig.DEBUG

/**
 * Define tag for logging - gets the name of the class that is being logged in
 */
private val Any.tag
    get() = javaClass.simpleName

fun Any.logD(message: String) = apply {
    if (DEBUG)
        Log.d(this.tag, message)
}

fun Any.logW(message: String) = apply {
    if (DEBUG)
        Log.w(this.tag, message)
}

fun Any.logWTF(message: String) = apply {
    if (DEBUG)
        Log.wtf(this.tag, message)
}


fun Any.logI(message: String) = apply {
    if (DEBUG)
        Log.wtf(this.tag, message)
}

fun Any.logE(message: String) = apply {
    if (DEBUG)
        Log.e(this.tag, message)
}


/**
 * Logs only will be shown in DEBUG mode with custom tag
 *
 */
fun Any.log(tag: String, message: String) {
    if (DEBUG)
        Log.d("$tag | ${this.tag}", message)
}

/**
 * Toast messages to be displayed only in DEBUG mode
 */
fun Context.showDebugToast(message: String) {
    if (DEBUG) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


