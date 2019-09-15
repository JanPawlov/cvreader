package com.triive.cvreader.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Resumesjson(
    @field:Json(name = "content")
    val content: ResponseResumeList
)
