package com.nibado.example.dgs.moviedb

import com.nibado.example.dgs.moviedb.dto.MovieCreditsDto
import com.nibado.example.dgs.moviedb.dto.MovieDto
import com.nibado.example.dgs.moviedb.dto.MoviesDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component

@Component
class MovieClient(template: RestTemplateBuilder) : ClientBase(template) {
    fun getTopRated() : List<MovieDto> = doGet("/movie/top_rated", MoviesDto::class.java).body!!.results
    fun getMovie(id: Int) : MovieDto = doGet("/movie/$id", MovieDto::class.java).body!!
    fun getMovieCredits(id: Int) : MovieCreditsDto =
        doGet("/movie/$id/credits", MovieCreditsDto::class.java).body!!
}