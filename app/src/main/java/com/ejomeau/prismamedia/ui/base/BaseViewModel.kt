package com.ejomeau.prismamedia.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent

/**
 * BaseViewModel
 *
 * - Provides uiScope to launch coroutines
 * - Exposes a ViewState through a LiveData to emit any view state change
 */

abstract class BaseViewModel : ViewModel(), KoinComponent {



    //Rx scope
    val compositeDisposable = CompositeDisposable()

    private val _viewState: MutableLiveData<BaseViewState> = MutableLiveData()
    val viewState: LiveData<BaseViewState>
        get() = _viewState

    fun postViewState(state: BaseViewState) {
        _viewState.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onPaused(){
        compositeDisposable.clear()
    }

    fun onDestroy(){
        compositeDisposable.dispose()
    }

}