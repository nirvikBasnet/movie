package com.elitecodecamp.movies.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.elitecodecamp.movies.data.model.Movie
import com.elitecodecamp.movies.presentation.ui.navigation.MovieScreens

@Composable
fun MovieList(viewModel: MovieViewModel,navController: NavController){
    val movieData by viewModel.movieData.collectAsState()
    LaunchedEffect(Unit){
        viewModel.fetchMovieData()
    }
    movieData?.let { MainContent(navController,it.Movies) }

}


@Composable
fun MainContent(navController: NavController,movieList: List<Movie>){

    Column(
        modifier = Modifier.padding(10.dp)
    ){
        LazyColumn {
            items(movieList){ Movie ->

                MovieRow(Movie){
                        id ->
                    //Log.d("TAG",movie)
                    navController.navigate(MovieScreens.DetailScreen.name+"/$id")
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier.padding(4.dp).fillMaxWidth()
            .clickable {
               onItemClick(movie.id)
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
            Column(modifier = Modifier.padding(4.dp)) {
                Text(movie.titleText.text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Release Date : ${movie.releaseDate.day}/${movie.releaseDate.month}/${movie.releaseDate.year}",
                style = MaterialTheme.typography.bodyMedium
                    )

                AnimatedVisibility(visible = expanded){
                    Column (){
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp),
                            ){
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(color = Color.DarkGray,
                                    fontSize = 13.sp,
                                fontWeight = FontWeight.Light),
                            ){
                                append(movie.titleText.text)
                            }

                        },
                        modifier = Modifier.padding(6.dp))

                        Divider()
                        Text(text = "Director: ${movie.primaryImage.url}")
                    }
                }

                Icon(imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier.size(25.dp)
                        .clickable {
                                   expanded = !expanded
                        },
                    tint = Color.DarkGray
                )
            }

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




