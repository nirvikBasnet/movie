package com.elitecodecamp.movies.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.elitecodecamp.movies.data.model.Movie

@Composable
fun MovieList(viewModel: MovieViewModel){
    val movieData by viewModel.movieData.collectAsState()
    LaunchedEffect(Unit){
        viewModel.fetchMovieData()
    }
    movieData?.let { MainContent(it.Movies) }
}


@Composable
fun MainContent(movieList: List<Movie>){

    Column(
        modifier = Modifier.padding(12.dp)
    ){
        LazyColumn {
            items(movieList){

                MovieRow(it){
                        movie ->
                    Log.d("TAG",movie)
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit){
    Card(
        modifier = Modifier.padding(4.dp).fillMaxWidth()
            .height(130.dp)
            .clickable {
               // onItemClick(movie)
            },
        shape = RoundedCornerShape(CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape
            ) {

                ConditionalImage(
                    model = movie.primaryImage?.url, // Assuming primaryImage is a nullable property
                    contentDescription = "${movie.titleText} image"
                )

            }
            Text(movie.titleText.text)
        }

    }
}

@Composable
fun ConditionalImage(model: String?, contentDescription: String) {
    if (model != null) {
        SubcomposeAsyncImage(
            model = model,
            contentDescription = contentDescription
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    } else {
        // You can place a placeholder or some default content here
    }
}



