package com.triive.cvreader.api.response

import com.squareup.moshi.Json

data class Resumesjson(
    @field:Json(name = "content")
    val content: ResponseResumeList
)
