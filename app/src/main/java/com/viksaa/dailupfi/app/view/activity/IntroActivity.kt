package com.viksaa.dailupfi.app.view.activity


import androidx.appcompat.app.AppCompatActivity
import com.viksaa.dailupfi.app.extensions.showDebugToast

class IntroActivity() : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        applicationContext.showDebugToast("INTRO TIME!")



    }
}