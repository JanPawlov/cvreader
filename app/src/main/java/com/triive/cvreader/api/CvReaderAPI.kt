package com.triive.cvreader.api

import com.triive.cvreader.api.response.ResponseGist
import retrofit2.Call
import retrofit2.http.GET

interface CvReaderAPI {

    @GET("/gists/7a5cd66a8a5bad574e575b57597da048")
    fun fetchResumes(): Call<ResponseGist>
}
