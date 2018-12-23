package com.viksaa.dailupfi.app.presenter

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {
    private var viewRef: WeakReference<V>? = null

    override fun getView(): V? = if (viewRef == null) null else viewRef?.get()

    override fun attachView(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }
}