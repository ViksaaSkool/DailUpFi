package com.viksaa.dailupfi.app.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viksaa.dailupfi.app.extensions.then
import com.viksaa.dailupfi.app.presenter.base.BaseContract

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(), BaseContract.View {


    protected val presenter: P by lazy { createPresenter() }
    protected abstract fun createPresenter(): P
    protected var layoutId = -1

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attachView(this as V)
    }

    abstract fun setLayout()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setLayout()
        return (layoutId == -1) then super.onCreateView(inflater, container, savedInstanceState)
                ?: inflater.inflate(layoutId, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}