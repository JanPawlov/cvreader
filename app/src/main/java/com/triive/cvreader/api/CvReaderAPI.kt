package com.triive.cvreader.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface CvReaderAPI {

    @GET("resumes")
    fun fetchResumes(): Call<ResponseBody>
}
