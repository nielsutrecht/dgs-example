package com.nibado.example.dgs.moviedb.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class MoviesDto(val page: Int, val results: List<MovieDto>)

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val runtime: Int?,
    @JsonProperty("release_date") val releaseDate: LocalDate,
    val popularity: Double
)

data class MovieCreditsDto(val id: Int, val cast: List<CastCredit>)

data class CastCredit(
    val id: Int,
    val gender: Int,
    val name: String,
    val character: String,
    @JsonProperty("credit_id") val creditId: String
)