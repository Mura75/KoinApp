package com.test.koinapp.presentation.common

sealed class CommonState {
    object ShowLoading : CommonState()
    object HideLoading : CommonState()
    data class Error(val error: String?) : CommonState()
    data class Exception(val error: Throwable) : CommonState()
}