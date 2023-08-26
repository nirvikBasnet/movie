package com.elitecodecamp.movies.data.network

import com.elitecodecamp.movies.data.model.MovieDto
import retrofit2.http.GET

interface MovieApiService {
    @GET("titles/x/upcoming")
    suspend fun getMovies():MovieDto
}