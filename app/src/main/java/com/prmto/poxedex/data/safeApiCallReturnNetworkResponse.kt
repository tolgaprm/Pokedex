package com.prmto.poxedex.data

import com.prmto.poxedex.common.NetworkResponse
import retrofit2.Response

suspend inline fun <T : Any> safeApiCallReturnNetworkResponse(
    crossinline apiCall: suspend () -> Response<T>,
): NetworkResponse<T> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let { NetworkResponse.Success(it) }
                ?: NetworkResponse.Error(NETWORK_ERROR)
        } else {
            NetworkResponse.Error(response.message())
        }
    } catch (e: Exception) {
        NetworkResponse.Error(e.message ?: NETWORK_ERROR)
    }
}

const val NETWORK_ERROR = "Something went wrong"