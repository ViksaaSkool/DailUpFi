package com.viksaa.dailupfi.app.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.viksaa.dailupfi.app.BuildConfig
import com.viksaa.dailupfi.app.BuildConfig.DEBUG

/**
 * Define tag for logging - gets the name of the class that is being logged in
 */
private val Any.tag
    get() = javaClass.simpleName

fun Any.log(context: Any, message: String) = apply {
    if (BuildConfig.DEBUG)
        Log.d(context.tag, message)
}

/**
 * Toast messages to be displayed only in DEBUG mode
 */
fun Context.showDebugToast(message: String) {
    if (DEBUG) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Logs only will be shown in DEBUG mode with custom tag
 *
 */
fun log(tag: String, message: String) {
    if (DEBUG)
        Log.d(tag, message)
}
