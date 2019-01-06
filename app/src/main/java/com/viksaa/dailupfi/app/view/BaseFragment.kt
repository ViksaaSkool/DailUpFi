package com.viksaa.dailupfi.app.view

import android.content.Context
import androidx.fragment.app.Fragment
import com.viksaa.dailupfi.app.presenter.BaseContract

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(), BaseContract.View {


    protected val presenter: P by lazy { createPresenter() }
    protected abstract fun createPresenter(): P

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}