package com.triive.cvreader.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resume(
    @field:Json(name = "first_name")
    val firstName: String,
    @field:Json(name = "last_name")
    val lastName: String,
    @field:Json(name = "photo")
    val photo: String,
    @field:Json(name = "position")
    val position: String
) : Parcelable
