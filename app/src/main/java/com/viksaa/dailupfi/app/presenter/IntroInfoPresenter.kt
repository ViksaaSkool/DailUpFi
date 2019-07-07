package com.viksaa.dailupfi.app.presenter

import com.viksaa.dailupfi.app.presenter.base.BasePresenter

class IntroInfoPresenter : BasePresenter<IntroInfoContract.View>(), IntroInfoContract.Presenter {


    override fun loadThings() {


        getView()?.showThings()
    }

}