package com.viksaa.dailupfi.app.view.activity


import android.os.Handler
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.launchActivity
import com.viksaa.dailupfi.app.presenter.IntroContract
import com.viksaa.dailupfi.app.presenter.IntroPresenter
import com.viksaa.dailupfi.app.view.base.BaseActivity

class IntroActivity : BaseActivity<IntroContract.View, IntroPresenter>(), IntroContract.View {

    override fun createPresenter(): IntroPresenter {
        return IntroPresenter()
    }

    override fun setLayout() {
        setContentView(R.layout.activity_intro)
    }


    override fun onStart() {
        super.onStart()

        Handler().postDelayed({
            launchActivity<HomeActivity>()
        }, 3000)
    }

    override fun showThings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}