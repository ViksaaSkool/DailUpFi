package com.viksaa.dailupfi.app.view.fragment

import com.viksaa.dailupfi.app.presenter.IntroSplashContract
import com.viksaa.dailupfi.app.presenter.IntroSplashPresenter
import com.viksaa.dailupfi.app.view.base.BaseFragment

class IntroSplashFragment : BaseFragment<IntroSplashContract.View, IntroSplashPresenter>(), IntroSplashContract.View {


    override fun createPresenter(): IntroSplashPresenter {
        return IntroSplashPresenter()
    }

    override fun showThings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}