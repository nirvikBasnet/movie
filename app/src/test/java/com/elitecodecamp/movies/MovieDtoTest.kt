package com.elitecodecamp.movies

import com.elitecodecamp.movies.data.model.Movie
import com.elitecodecamp.movies.data.model.MovieDto
import com.elitecodecamp.movies.data.model.PrimaryImage
import com.elitecodecamp.movies.data.model.ReleaseDate
import com.elitecodecamp.movies.data.model.TitleText
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieDtoTest {

    @Test
    fun testMovieDtoProperties(){
        val id="123abc"
        val primaryImage= PrimaryImage("www.movie.com/1.png")
        val releaseDate = ReleaseDate(1,1,2023)
        val titleText = TitleText("Test Title")

        val movies = listOf<Movie>(Movie(id,primaryImage,titleText,releaseDate))

        assertEquals(id, movies.get(0).id)
        assertEquals(primaryImage, movies.get(0).primaryImage)
        assertEquals(releaseDate, movies.get(0).releaseDate)
        assertEquals(titleText, movies.get(0).titleText)


    }

}