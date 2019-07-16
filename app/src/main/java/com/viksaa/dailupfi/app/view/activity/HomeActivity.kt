package com.viksaa.dailupfi.app.view.activity

import android.os.Handler
import android.os.Looper
import android.widget.CompoundButton
import com.viksaa.dailupfi.app.DailUpFiApplication
import com.viksaa.dailupfi.app.R
import com.viksaa.dailupfi.app.extensions.*
import com.viksaa.dailupfi.app.network.DailUpFiNetworkReceiver
import com.viksaa.dailupfi.app.network.listeners.DailUpFiAnimationListener
import com.viksaa.dailupfi.app.presenter.HomeContract
import com.viksaa.dailupfi.app.presenter.HomePresenter
import com.viksaa.dailupfi.app.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeContract.View, HomePresenter>(), HomeContract.View, DailUpFiAnimationListener, CompoundButton.OnCheckedChangeListener {

    private lateinit var dailUpFiNetworkReceiver: DailUpFiNetworkReceiver
    private val animationHandler = Handler(Looper.getMainLooper())
    private val animationRunnable = Runnable {
        animation_view.stopAnimation()
    }

    override fun setLayout() {
        setContentView(R.layout.activity_home)
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun onStart() {
        super.onStart()
        setUI()
        setDailUpFi()
    }

    override fun onConnecting() {
        animationHandler.postDelayed(animationRunnable, WAVES_ANIMATION_DURATION)
        animation_view.startAnimation()
    }

    override fun onDisconnected() {
        animation_view.stopAnimation()
        animationHandler.removeCallbacks(animationRunnable)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        logD("onCheckedChanged() | isChecked = $isChecked")
        switchDailUpFiService(isChecked)
        root_home_layout.startAnimationOnOff(isChecked)
        setTheme(isDailUpFiOn())
        status_text_view.setText(isChecked)
        if (!isChecked) {
            animation_view.stopAnimation()
        }
    }

    private fun switchDailUpFiService(switch: Boolean) {
        presenter.turnDailUpFi(applicationContext, switch)
    }

    /** UI set methods **/

    private fun setDailUpFi() {
        dailUpFiNetworkReceiver = (application as DailUpFiApplication).getDailUpFiNetworkReceiver()
        dailUpFiNetworkReceiver.addDailupfiListner(this)
    }

    private fun setUI() {
        dail_up_fi_switch.isChecked = isDailUpFiOn()
        dail_up_fi_switch.setOnCheckedChangeListener(this)
        root_home_layout.setBackground(isDailUpFiOn())
        setTheme(isDailUpFiOn())
        status_text_view.setText(isDailUpFiOn())

    }

    override fun onDestroy() {
        super.onDestroy()
        animationHandler.removeCallbacks(animationRunnable)
        dailUpFiNetworkReceiver.removeDailupfiListner(this)
    }
}