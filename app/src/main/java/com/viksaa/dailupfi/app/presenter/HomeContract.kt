package com.viksaa.dailupfi.app.presenter

import android.content.Context
import com.viksaa.dailupfi.app.presenter.base.BaseContract

class HomeContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun turnDailUpFi(context: Context, switch: Boolean)
    }
}