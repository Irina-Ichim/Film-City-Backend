package com.example.filmcity.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(private val movieRepository: MovieRepository) {
    @GetMapping("/localhost:8080/MovieController")
    fun allMovies(): List<Movie?>? {
        return movieRepository.findAll()
    }
}



