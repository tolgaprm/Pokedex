package com.prmto.poxedex.common

sealed class NetworkResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Error(val error: String) : NetworkResponse<Nothing>()
}