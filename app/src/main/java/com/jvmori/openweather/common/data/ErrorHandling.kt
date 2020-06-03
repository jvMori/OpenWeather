package com.jvmori.openweather.common.data

import android.accounts.NetworkErrorException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <Result> handleError(e: Throwable?): Resource<Result> {
    if (e is NetworkErrorException || e is SocketTimeoutException || e is HttpException || e is UnknownHostException) {
        return Resource.networkError(
            null,
            e.localizedMessage
        )
    }
    return Resource.error(
        e?.localizedMessage ?: "",
        null
    )
}