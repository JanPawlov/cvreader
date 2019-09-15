package com.triive.cvreader.api

import com.triive.cvreader.api.response.ResponseGist
import com.triive.cvreader.utils.extensions.awaitOutcome

class ApiRepository(private val apiFactory: ApiFactory) {

    private fun getApi() = apiFactory.createApi()

    suspend fun fetchResumes(): Outcome<ResponseGist, ResponseEmpty> = getApi().fetchResumes().awaitOutcome()
}
typealias ResponseEmpty = Any
