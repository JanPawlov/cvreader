package com.triive.cvreader.api.response

import com.squareup.moshi.Json

data class Files(
    @field:Json(name = "resumes.json")
    val resumesjson: Resumesjson
)
