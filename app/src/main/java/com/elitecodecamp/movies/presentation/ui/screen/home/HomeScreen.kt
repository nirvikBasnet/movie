package com.elitecodecamp.movies.presentation.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.elitecodecamp.movies.presentation.ui.screen.MovieList
import com.elitecodecamp.movies.presentation.ui.screen.MovieViewModel
import com.elitecodecamp.movies.presentation.ui.theme.MoviesTheme

@Composable
fun HomeScreen(navController: NavController,viewModel: MovieViewModel){
    MyApp {
        MovieList(viewModel)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content: @Composable () ->Unit ){
    MoviesTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Movies") },
                    colors = TopAppBarDefaults.largeTopAppBarColors(Color.Magenta)
                )
            }
        ){
            Box(
                modifier = Modifier.padding(it)
            ) {
                content()
            }


        }
    }
}

