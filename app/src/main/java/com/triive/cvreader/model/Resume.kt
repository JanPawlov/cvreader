package com.triive.cvreader.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.triive.cvreader.api.response.Employement
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
    val position: String,
    @field:Json(name = "phone")
    val phone: Int,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "profile")
    val profile: String,
    @field:Json(name = "skills")
    val skills: List<String>,
    @field:Json(name = "hobbies")
    val hobbies: String,
    @field:Json(name = "languages")
    val languages: String,
    @field:Json(name = "employement")
    val employement: List<Employement>
) : Parcelable
