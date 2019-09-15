package com.triive.cvreader.api.response.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.triive.cvreader.api.response.ResponseResumeList

class ResumeListAdapter {
    @FromJson
    fun fromJson(string: String): ResponseResumeList? {
        val adapter = Moshi.Builder().build().adapter(ResponseResumeList::class.java)
        return adapter.fromJson(string)
    }
}
