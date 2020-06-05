package com.jvmori.openweather.common.util

import android.accounts.NetworkErrorException
import com.jvmori.openweather.common.data.network.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <Result> handleError(e: Throwable?, data : Result? = null): Resource<Result> {
    if (e is NetworkErrorException || e is SocketTimeoutException || e is HttpException || e is UnknownHostException) {
        return Resource.networkError(
            data,
            e.localizedMessage
        )
    }
    return Resource.error(
        e?.localizedMessage ?: "",
        data
    )
}