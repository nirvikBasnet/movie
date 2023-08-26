package com.elitecodecamp.movies.presentation.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elitecodecamp.movies.data.model.MovieDto
import com.elitecodecamp.movies.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repositoryImpl: MovieRepositoryImpl
):ViewModel() {
    private val _movieData = MutableStateFlow<MovieDto?>(null)
    var movieData : StateFlow<MovieDto?> = _movieData

    fun fetchMovieData(){
        viewModelScope.launch {
            val response = repositoryImpl.getMovieData()
            _movieData.value = response.data
        }
    }

}