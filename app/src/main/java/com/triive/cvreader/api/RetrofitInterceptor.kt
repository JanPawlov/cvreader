package com.triive.cvreader.api

import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().newBuilder().apply {
            header("Accept", "application/json")
            header("Content-Type", "application/json")
            header("Cache-Control", "no-cache")
        }.build())
}
