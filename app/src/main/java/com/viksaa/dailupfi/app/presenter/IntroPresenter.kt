package com.viksaa.dailupfi.app.presenter

import com.viksaa.dailupfi.app.presenter.base.BasePresenter

class IntroPresenter : BasePresenter<IntroContract.View>(), IntroContract.Presenter {


    override fun loadThings() {


        getView()?.showThings()
    }

}