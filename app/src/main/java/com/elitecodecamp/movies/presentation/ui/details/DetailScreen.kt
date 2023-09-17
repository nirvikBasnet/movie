package com.elitecodecamp.movies.presentation.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.elitecodecamp.movies.data.model.Movie
import com.elitecodecamp.movies.presentation.ui.screen.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,viewModel: MovieViewModel,movieId: String?){

    val movieData by viewModel.movieData.collectAsState()

    var movie: Movie? = null

    LaunchedEffect(Unit){
        viewModel.fetchMovieData()
    }

    movieData?.let {
        movie = it.Movies.filter {
            it.id.equals(movieId)
        }.first()
    }




    Scaffold (
        topBar = {
            TopAppBar(
                title = { movie?.titleText?.text } ,
                colors = TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
                navigationIcon = {
                    if (navController.previousBackStackEntry != null){
                        IconButton(onClick = {navController.popBackStack()}){
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }else{
                        null
                    }
                }
            )
        }
            ){

        Surface(modifier = Modifier.fillMaxHeight()
            .fillMaxWidth()){
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){

                movie?.titleText?.let { it1 ->
                    Text(text = it1.text, style = MaterialTheme
                        .typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(23.dp))
            }

        }

    }



}