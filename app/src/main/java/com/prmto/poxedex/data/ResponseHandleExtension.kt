package com.prmto.poxedex.data

import com.prmto.poxedex.common.NetworkResponse

fun <I : Any, O : Any> NetworkResponse<I>.mapResponse(mapper: (I) -> O): NetworkResponse<O> {
    return when (this) {
        is NetworkResponse.Success -> NetworkResponse.Success(mapper.invoke(this.data))
        is NetworkResponse.Error -> NetworkResponse.Error(error)
    }
}