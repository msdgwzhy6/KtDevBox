package com.cysion.ktbox.base

import com.cysion.ktbox.listener.IView
import com.cysion.ktbox.net.ApiException
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : IView> {
    var attchedView: V? = null
        private set

    protected val compositeDisposable = CompositeDisposable()

    fun attach(v: V) {
        attchedView = v
    }

    fun detach() {
        attchedView = null
        compositeDisposable.clear()
    }

    fun error(error:ApiException) {
        attchedView?.error(error.errorCode, error.errorMsg)
    }

    fun checkViewAttached() {
        if (attchedView == null) {
            throw ViewNotAttchedException()
        }
    }
}

private class ViewNotAttchedException() : Exception("Please call attach method firstly")