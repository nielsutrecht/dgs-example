package com.nibado.example.dgs.fetcher

import com.netflix.graphql.dgs.*
import com.nibado.example.dgs.moviedb.MovieClient
import com.nibado.example.dgs.moviedb.dto.MovieDto


@DgsComponent
class MoviesDataFetcher(private val movieClient: MovieClient) {

    @DgsQuery
    fun movies(@InputArgument titleFilter: String?) : List<Movie> =
        movieClient.getTopRated().filter {
            if(titleFilter != null) it.title.contains(titleFilter) else true
        }.map { it.toDomain() }

    @DgsQuery
    fun movie(@InputArgument id: Int) : Movie = movieClient
        .getMovie(id).let { it.toDomain()}

    @DgsData(parentType = "Movie", field = "cast")
    fun cast(dfe: DgsDataFetchingEnvironment): List<CastMember> {
        val movie: Movie = dfe.getSource()
        val credits = movieClient.getMovieCredits(movie.id)
        credits.cast.map { CastMember(it.creditId, Actor(it.id, it.name), it.character) }

        return credits.cast.map { CastMember(it.creditId, Actor(it.id, it.name), it.character) }
    }
}

private fun MovieDto.toDomain() = Movie(id, title, overview, runtime, releaseDate, popularity, emptyList())