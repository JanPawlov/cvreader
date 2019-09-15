package com.triive.cvreader.api

import com.triive.cvreader.api.response.ResponseGist
import com.triive.cvreader.api.response.ResponseResumeList
import com.triive.cvreader.utils.extensions.awaitOutcome
import okhttp3.ResponseBody

class ApiRepository(private val apiFactory: ApiFactory) {

    private fun getApi() = apiFactory.createApi()

    suspend fun fetchResumes(): Outcome<ResponseGist, ResponseEmpty> = getApi().fetchResumes().awaitOutcome()
}
typealias ResponseEmpty = Any
