package com.viksaa.dailupfi.app.view.activity


import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.replaceFragment
import com.viksaa.dailupfi.app.presenter.IntroContract
import com.viksaa.dailupfi.app.presenter.IntroPresenter
import com.viksaa.dailupfi.app.view.base.BaseActivity
import com.viksaa.dailupfi.app.view.fragment.IntroSplashFragment

class IntroActivity : BaseActivity<IntroContract.View, IntroPresenter>(), IntroContract.View {

    override fun createPresenter(): IntroPresenter {
        return IntroPresenter()
    }

    override fun setLayout() {
        setContentView(R.layout.activity_intro)
    }


    override fun onStart() {
        super.onStart()
        replaceFragment(IntroSplashFragment(), R.id.intro_fragment_frame_layout)
    }

    override fun showThings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}