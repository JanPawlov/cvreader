package com.triive.cvreader.api.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employement(
    @field:Json(name = "position")
    val position: String,
    @field:Json(name = "employer")
    val employer: String,
    @field:Json(name = "from")
    val from: String,
    @field:Json(name = "to")
    val to: String,
    @field:Json(name = "responsibilities")
    val responsibilities: List<String>
) : Parcelable
