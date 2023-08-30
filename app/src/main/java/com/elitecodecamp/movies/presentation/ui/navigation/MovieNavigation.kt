package com.elitecodecamp.movies.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elitecodecamp.movies.presentation.ui.screen.MovieViewModel
import com.elitecodecamp.movies.presentation.ui.screen.home.HomeScreen

//navController, NavHost & NavGraph
//Everything related to Navigation is in this file
@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    val viewModel: MovieViewModel = viewModel()
    //NavHost and NavGraph
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name){
            //we pass where this should lead us to
            HomeScreen(navController,viewModel)
        }
    }
}

