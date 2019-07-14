package com.viksaa.dailupfi.app.extensions

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.AnimationDrawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.viksaa.dailupfi.app.R


const val ENTER_FADE_DURATION = 800
const val EXIT_FADE_DURATION = 800
const val WAVES_ANIMATION_DURATION = 27600L

fun View.hide(isGone: Boolean = false) {
    visibility = isGone then View.GONE ?: View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Starts playing the background gradient animation
 *
 * @param isOn - use the flag to set appropriate drawable resource and start the animation
 */
fun View.startAnimationOnOff(isOn: Boolean) {
    background = if (isOn) {
        context.getDrawable(com.viksaa.dailupfi.app.R.drawable.background_gradient_animation_on)
    } else {
        context.getDrawable(com.viksaa.dailupfi.app.R.drawable.background_gradient_animation_off)
    }
    (background as AnimationDrawable).startAnimationOnOff()
}

/**
 * Plays animation with set parameters
 */
private fun AnimationDrawable.startAnimationOnOff() {
    setEnterFadeDuration(ENTER_FADE_DURATION)
    setExitFadeDuration(EXIT_FADE_DURATION)
    isOneShot = true
    start()
}

@SuppressLint("RestrictedApi")
fun LottieAnimationView.startAnimation() {
    addValueCallback(KeyPath("**"), LottieProperty.COLOR_FILTER)
    { PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP) }
    show()
    loop(true)
    setFadingEdgeLength(500)
    playAnimation()

}

fun LottieAnimationView.stopAnimation() {
    cancelAnimation()
    hide(false)
}

fun AppCompatActivity.setTheme(isOn: Boolean) {
    if (isOn) {
        setTheme(R.style.DailupFiOnTheme)
    } else {
        setTheme(R.style.DailupFiOffTheme)
    }
}

fun View.setBackground(isOn: Boolean) {
    background = if (isOn) {
        context.getDrawable(com.viksaa.dailupfi.app.R.drawable.background_gradient_animation_off)
    } else {
        context.getDrawable(com.viksaa.dailupfi.app.R.drawable.background_gradient_animation_on)
    }
}
