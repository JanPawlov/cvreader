package com.triive.cvreader.api

import okhttp3.Headers

sealed class Outcome<out T, out E> {
    data class Success<T, E>(val data: T, val headers: Headers) : Outcome<T, E>()
    data class Failure<T, E>(val failureBody: E, val code: Int) : Outcome<T, E>()
    data class Error<T, E>(val throwable: Throwable) : Outcome<T, E>()
}
