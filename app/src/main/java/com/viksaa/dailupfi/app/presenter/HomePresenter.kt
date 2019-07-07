package com.viksaa.dailupfi.app.presenter

import android.content.Context
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.extensions.startService
import com.viksaa.dailupfi.app.extensions.stopService
import com.viksaa.dailupfi.app.network.DailUpFiNetworkService
import com.viksaa.dailupfi.app.presenter.base.BasePresenter

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {


    override fun turnDailUpFi(context: Context, switch: Boolean) {
        logD("turnDailUpFi() | switch value = $switch")
        if (switch) {
            context.startService<DailUpFiNetworkService>()
        } else {
            context.stopService<DailUpFiNetworkService>()
        }
    }
}