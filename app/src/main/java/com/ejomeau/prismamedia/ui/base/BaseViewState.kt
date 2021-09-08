package com.ejomeau.prismamedia.ui.base

/**
 * BaseViewState
 *
 * Describes common view state
 */
sealed class BaseViewState {

    data class Error(val message: String) :
        BaseViewState()

    data class Success(val message: String = "Success") :
        BaseViewState()

    object StartLoading : BaseViewState()

    object StopLoading : BaseViewState()
}