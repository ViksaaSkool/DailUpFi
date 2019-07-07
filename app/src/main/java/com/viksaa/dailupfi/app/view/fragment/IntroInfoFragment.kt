package com.viksaa.dailupfi.app.view.fragment

import com.viksaa.dailupfi.app.presenter.IntroInfoContract
import com.viksaa.dailupfi.app.presenter.IntroInfoPresenter
import com.viksaa.dailupfi.app.view.base.BaseFragment

class IntroInfoFragment : BaseFragment<IntroInfoContract.View, IntroInfoPresenter>(), IntroInfoContract.View {

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showThings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun createPresenter(): IntroInfoPresenter {
        return IntroInfoPresenter()
    }
}