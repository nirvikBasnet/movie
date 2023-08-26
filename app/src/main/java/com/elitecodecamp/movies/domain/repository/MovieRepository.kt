package com.elitecodecamp.movies.domain.repository

import com.elitecodecamp.movies.common.Resource
import com.elitecodecamp.movies.data.model.MovieDto

interface MovieRepository {
    suspend fun getMovieData():Resource<MovieDto>
}