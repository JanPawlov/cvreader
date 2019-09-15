package com.triive.cvreader.api.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ResponseGist(
    @field:Json(name = "files")
    val files: Files
)
