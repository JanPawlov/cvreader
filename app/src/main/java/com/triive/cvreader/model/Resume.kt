package com.triive.cvreader.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Resume(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "position")
    val position: String
) : Parcelable {
    companion object {
        private val mock = Resume(
            firstName = "John",
            lastName = "Doe",
            photo = "https://portrait-gemalt-nach-foto.de/wp-content/uploads/2018/07/big_36375186_0_592-557-min.jpg",
            position = "Android Developer"
        )
        private val mock2 = Resume(
            firstName = "John2",
            lastName = "Doe2",
            photo = "https://media.gettyimages.com/photos/portrait-of-smiling-young-man-wearing-eyeglasses-picture-id985138634?s=612x612",
            position = "Android Developer2"
        )
        private val mock3 = Resume(
            firstName = "John3",
            lastName = "Doe3",
            photo = "https://media.gettyimages.com/photos/portrait-of-smiling-mid-adult-man-wearing-tshirt-picture-id985138674?s=612x612",
            position = "Android Developer3"
        )
        val mockedResumes = listOf(mock, mock2, mock3)
    }
}
