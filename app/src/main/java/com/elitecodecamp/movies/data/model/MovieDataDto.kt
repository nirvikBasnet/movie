package com.elitecodecamp.movies.data.model

import com.squareup.moshi.Json

data class Movie(
    @field:Json(name="id")
    val id: String,
    @field:Json(name="primaryImage")
    val primaryImage: PrimaryImage,
    @field:Json(name="titleText")
    val titleText: TitleText,
    @field:Json(name="releaseDate")
    val releaseDate: ReleaseDate

)

data class TitleText (
    @field:Json(name="text")
    val text:String
        )

data class PrimaryImage(
    @field:Json(name="url")
    val url:String
)

data class ReleaseDate(
    @field:Json(name = "day")
    val day: Int,
    @field:Json(name="month")
    val month: Int,
    @field:Json(name = "year")
    val year: Int
)
