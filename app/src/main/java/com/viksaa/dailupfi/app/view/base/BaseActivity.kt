package com.viksaa.dailupfi.app.view.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.viksaa.dailupfi.app.presenter.base.BaseContract

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(), BaseContract.View {

    protected val presenter: P by lazy { createPresenter() }
    protected abstract fun createPresenter(): P

    //This happens under the hood in java.
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    abstract fun setLayout()
}