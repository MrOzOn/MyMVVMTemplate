package com.mrozon.core_mvvm.base

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, Effect>: ViewModel(){

    abstract fun catchError(info: String)

    abstract fun initialUI(): State

    protected val _state = MutableStateFlow(initialUI())
    val state = _state.asStateFlow()

    protected val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    protected val errorHandler = CoroutineExceptionHandler { _, exception ->
        catchError(exception.message?:"unknown error")
    }

}

abstract class BaseViewModelObservable<State, Effect>: BaseViewModel<State, Effect>(), Observable {
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}