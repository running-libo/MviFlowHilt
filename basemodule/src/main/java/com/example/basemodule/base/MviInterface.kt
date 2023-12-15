package com.example.basemodule.base

import androidx.annotation.Keep

@Keep
interface IUiState

@Keep
interface IUiIntent //event

sealed class LoadUiIntent {
    data class Loading(var isShow: Boolean) : LoadUiIntent()
    object ShowPageView : LoadUiIntent()
    data class Error(val msg: String) : LoadUiIntent()
}