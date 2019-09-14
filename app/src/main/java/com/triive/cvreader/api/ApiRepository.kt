package com.triive.cvreader.api

import com.triive.cvreader.extensions.awaitOutcome
import okhttp3.ResponseBody

class ApiRepository(private val apiFactory: ApiFactory) {

    private fun getApi() = apiFactory.createApi()

    suspend fun fetchResumes(): Outcome<ResponseBody, ResponseEmpty> = getApi().fetchResumes().awaitOutcome()
}
typealias ResponseEmpty = Any
