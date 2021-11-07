package com.nibado.example.dgs.moviedb

import mu.KotlinLogging
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity

private val log = KotlinLogging.logger {}

abstract class ClientBase(templateBuilder: RestTemplateBuilder) {
    private val baseUrl = "https://api.themoviedb.org/3"
    private val apiKey = System.getenv("API_KEY")
    private val template = templateBuilder.build()

    init {
        if(apiKey == null) {
            throw IllegalStateException("Missing API_KEY env var")
        }
    }

    fun <T> doGet(uri: String, clazz: Class<T>) : ResponseEntity<T> {
        log.info { "GET $baseUrl$uri"}
        return template.getForEntity<T>("$baseUrl$uri?api_key=$apiKey", clazz)
    }
}