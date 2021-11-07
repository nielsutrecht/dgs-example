package com.nibado.example.dgs.fetcher

import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val runtime: Int?,
    val releaseDate: LocalDate,
    val popularity: Double,
    val cast: List<CastMember>
)
data class Actor(val id: Int, val name: String)
data class CastMember(val id: String, val actor: Actor, val character: String)