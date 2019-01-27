package com.viksaa.dailupfi.app.extensions

import android.view.View


fun View.hide(isGone: Boolean = false) {
    visibility = isGone then View.GONE ?: View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}


