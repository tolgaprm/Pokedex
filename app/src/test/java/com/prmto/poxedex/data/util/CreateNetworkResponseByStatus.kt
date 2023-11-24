package com.prmto.poxedex.data.util

import com.prmto.poxedex.common.NetworkResponse

inline fun <T : Any> createNetworkResponseByStatus(
    isReturnError: Boolean,
    onSuccessReturnData: () -> T?
): NetworkResponse<T> {
    return if (isReturnError) {
        NetworkResponse.Error("Error")
    } else {
        val data = onSuccessReturnData()
        data?.let {
            NetworkResponse.Success(data = data)
        } ?: run { NetworkResponse.Error("Error") }
    }
}