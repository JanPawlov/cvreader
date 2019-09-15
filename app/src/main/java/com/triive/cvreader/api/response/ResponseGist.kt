package com.triive.cvreader.api.response


import com.squareup.moshi.Json

data class ResponseGist(
    @field:Json(name = "files")
    val files: Files
)
