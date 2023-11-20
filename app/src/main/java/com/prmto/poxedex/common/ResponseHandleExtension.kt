package com.prmto.poxedex.common

fun <I : Any, O : Any> NetworkResponse<I>.mapResponse(mapper: (I) -> O): NetworkResponse<O> {
    return when (this) {
        is NetworkResponse.Success -> NetworkResponse.Success(mapper.invoke(this.data))
        is NetworkResponse.Error -> NetworkResponse.Error(error)
    }
}