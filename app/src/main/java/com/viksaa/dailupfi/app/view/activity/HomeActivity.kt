package com.viksaa.dailupfi.app.view.activity

import android.graphics.drawable.AnimationDrawable
import android.widget.CompoundButton
import com.viksaa.dailupfi.app.DailUpFiApplication
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.logD
import com.viksaa.dailupfi.app.network.DailUpFiNetworkReceiver
import com.viksaa.dailupfi.app.network.listeners.DailUpFiAnimationListener
import com.viksaa.dailupfi.app.presenter.HomeContract
import com.viksaa.dailupfi.app.presenter.HomePresenter
import com.viksaa.dailupfi.app.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeContract.View, HomePresenter>(), HomeContract.View, DailUpFiAnimationListener, CompoundButton.OnCheckedChangeListener {

    private lateinit var dailUpFiNetworkReceiver: DailUpFiNetworkReceiver
    private lateinit var animDrawable: AnimationDrawable

    override fun setLayout() {
        setContentView(R.layout.activity_home)
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun onStart() {
        super.onStart()
        dail_up_fi_switch.setOnCheckedChangeListener(this)
        animDrawable = root_layout.background as AnimationDrawable
        setDailUpFi()

    }

    override fun onConnecting() {
        animDrawable.setEnterFadeDuration(2000)
        animDrawable.setExitFadeDuration(1500)
        animDrawable.start()
    }

    override fun onDisconnected() {
        animDrawable.stop()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        logD("onCheckedChanged() | isChecked = $isChecked")
        switchDailUpFiService(isChecked)
    }


    override fun onDestroy() {
        super.onDestroy()
        dailUpFiNetworkReceiver.removeDailupfiListner(this)
    }


    private fun switchDailUpFiService(switch: Boolean) {
        presenter.turnDailUpFi(applicationContext, switch)
    }


    private fun setDailUpFi() {
        dailUpFiNetworkReceiver = (application as DailUpFiApplication).getDailUpFiNetworkReceiver()
        dailUpFiNetworkReceiver.addDailupfiListner(this)
    }
}