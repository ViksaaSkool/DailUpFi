package com.viksaa.dailupfi.app.presenter

import com.viksaa.dailupfi.app.presenter.base.BasePresenter

class IntroSplashPresenter : BasePresenter<IntroSplashContract.View>(), IntroSplashContract.Presenter {


    override fun loadThings() {


        getView()?.showThings()
    }

}