package com.viksaa.dailupfi.app.view.fragment

import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.presenter.IntroSplashContract
import com.viksaa.dailupfi.app.presenter.IntroSplashPresenter
import com.viksaa.dailupfi.app.view.activity.HomeActivity
import com.viksaa.dailupfi.app.view.base.BaseFragment
import com.viksaa.dailupfi.app.widget.TypeWriterViewListener
import kotlinx.android.synthetic.main.fragment_splash.*


class IntroSplashFragment : BaseFragment<IntroSplashContract.View, IntroSplashPresenter>(), IntroSplashContract.View, TypeWriterViewListener {

    companion object {
        private const val TRANSITION_DELAY = 1000L
    }

    private val transitionHandler = Handler(Looper.getMainLooper())
    private val transitionRunnable = Runnable {
        requireAppCompatActivity().apply {
            logD("isIntroSeen() = ${isIntroSeen()}")
            if (isIntroSeen()) {
                launchActivity<HomeActivity>(null, true)
            } else {
                replaceFragment(IntroInfoFragment(), R.id.intro_fragment_frame_layout)
            }
        }
    }

    override fun createPresenter(): IntroSplashPresenter {
        return IntroSplashPresenter()
    }

    override fun setLayout() {
        layoutId = R.layout.fragment_splash
    }

    override fun onStart() {
        super.onStart()
        startAnimation()
    }

    private fun startAnimation() {

        val text = getString(R.string.app_name)

        //set the logo
        ObjectAnimator.ofFloat(splash_logo, "alpha", 0.5f, 1.0f).apply {
            duration = SPLASH_TEXT_LETTER_DELAY * text.length
            start()
        }
        (splash_logo.background as AnimationDrawable).apply {
            isOneShot = false
            start()
        }

        //set the typewriter
        dail_up_fi_text_view.apply {
            delay = SPLASH_TEXT_LETTER_DELAY
            setTypeWriterViewListener(this@IntroSplashFragment)
            animateText(text)
        }
    }

    override fun onTypeWritingEnd(viewId: Int) {
        logD("onTypeWritingEnd() | it ended!")
        dail_up_fi_text_view?.let {
            transitionHandler.postDelayed(transitionRunnable, TRANSITION_DELAY)
        }

    }

    override fun onDetach() {
        transitionHandler.removeCallbacks(transitionRunnable)
        super.onDetach()
    }


}