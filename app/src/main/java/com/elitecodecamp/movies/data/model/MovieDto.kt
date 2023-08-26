package com.elitecodecamp.movies.data.model

import com.squareup.moshi.Json

data class MovieDto(
    @field:Json(name="results")
    val Movies : List<Movie>
)
