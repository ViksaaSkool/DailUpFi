package com.viksaa.dailupfi.app.presenter

import com.viksaa.dailupfi.app.presenter.base.BaseContract

class IntroInfoContract : BaseContract {

    interface View : BaseContract.View {
        fun showThings()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadThings()
    }

}