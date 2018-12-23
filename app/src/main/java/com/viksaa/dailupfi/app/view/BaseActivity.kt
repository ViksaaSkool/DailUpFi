package com.viksaa.dailupfi.app.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.viksaa.dailupfi.app.presenter.BaseContract

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(), BaseContract.View {

    protected val presenter: P by lazy { createPresenter() }
    protected abstract fun createPresenter(): P

    //This happens under the hood in java.
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}