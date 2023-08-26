package com.elitecodecamp.movies.data.repository
import com.elitecodecamp.movies.common.Resource
import com.elitecodecamp.movies.data.model.MovieDto
import com.elitecodecamp.movies.data.network.MovieApiService
import com.elitecodecamp.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService
) : MovieRepository {
    override suspend fun getMovieData(): Resource<MovieDto> {
        return try {
            Resource.Success(
                data = api.getMovies()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occured.")
        }
    }

}