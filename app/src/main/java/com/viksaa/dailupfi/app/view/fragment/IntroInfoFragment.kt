package com.viksaa.dailupfi.app.view.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.View
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.launchActivity
import com.viksaa.dailupfi.app.extensions.requireAppCompatActivity
import com.viksaa.dailupfi.app.extensions.setIntroSeen
import com.viksaa.dailupfi.app.extensions.show
import com.viksaa.dailupfi.app.presenter.IntroInfoContract
import com.viksaa.dailupfi.app.presenter.IntroInfoPresenter
import com.viksaa.dailupfi.app.view.activity.HomeActivity
import com.viksaa.dailupfi.app.view.base.BaseFragment
import com.viksaa.dailupfi.app.widget.TypeWriterViewListener
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*


class IntroInfoFragment : BaseFragment<IntroInfoContract.View, IntroInfoPresenter>(), IntroInfoContract.View, TypeWriterViewListener, View.OnClickListener {


    companion object {
        private const val TYPING_DURATION = 80L
        private const val ANSWER_DELAY = 1200L
        private const val BUTTON_ANIMATION_DURATION = 1000L
    }


    override fun setLayout() {
        layoutId = com.viksaa.dailupfi.app.R.layout.fragment_info
    }

    override fun createPresenter(): IntroInfoPresenter {
        return IntroInfoPresenter()
    }

    override fun onStart() {
        super.onStart()
        startAnimation()
    }


    private fun startAnimation() {

        letsgo_button.setOnClickListener(this)

        questions_text_view.apply {
            setTypeWriterViewListener(this@IntroInfoFragment)
            questions_text_view.delay = TYPING_DURATION
            questions_text_view.show()
            animateText(getString(com.viksaa.dailupfi.app.R.string.text_info_questions))
        }

    }

    override fun onTypeWritingEnd(viewId: Int) {
        when (viewId) {
            com.viksaa.dailupfi.app.R.id.questions_text_view -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    answers_text_view?.apply {
                        setTypeWriterViewListener(this@IntroInfoFragment)
                        answers_text_view.delay = TYPING_DURATION
                        answers_text_view.show()
                        animateText(getString(com.viksaa.dailupfi.app.R.string.text_info_answer))
                    }
                }, ANSWER_DELAY)

            }
            R.id.answers_text_view -> {
                val scaleY = ObjectAnimator.ofFloat(letsgo_button, "scaleY", 0.5f, 1.0f)
                val scaleX = ObjectAnimator.ofFloat(letsgo_button, "scaleX", 0.5f, 1.0f)
                val alpha = ObjectAnimator.ofFloat(letsgo_button, "alpha", 0.1f, 1.0f)

                AnimatorSet().apply {
                    playTogether(scaleX, scaleY, alpha)
                    duration = BUTTON_ANIMATION_DURATION
                    letsgo_button.show()
                    start()
                }
            }
        }
    }


    override fun onClick(v: View?) {
        v?.let { view ->
            if (view.id == R.id.letsgo_button) {
                requireAppCompatActivity().apply {
                    setIntroSeen(true)
                    launchActivity<HomeActivity>()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}