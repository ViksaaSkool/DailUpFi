package com.viksaa.dailupfi.app.presenter

import com.viksaa.dailupfi.app.presenter.base.BaseContract

class IntroContract : BaseContract {

    interface View : BaseContract.View {
    }

    interface Presenter : BaseContract.Presenter<View> {
    }

}