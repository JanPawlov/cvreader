package com.triive.cvreader.api.response

import com.squareup.moshi.Json
import com.triive.cvreader.model.Resume

data class ResponseResumeList(
    @field:Json(name = "resumes")
    val resumes: List<Resume>
)
