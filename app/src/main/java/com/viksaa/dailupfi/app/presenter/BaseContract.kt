package com.viksaa.dailupfi.app.presenter

interface BaseContract {

    interface View

    interface Presenter<V : View>{
        fun getView() : V?
        fun attachView(view : V)
        fun detachView()
    }
}