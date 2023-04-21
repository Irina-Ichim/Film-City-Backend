package com.example.filmcity.controllers

import com.example.filmcity.repositories.Movie
import com.example.filmcity.repositories.MovieRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(private val movieRepository: MovieRepository) {
    @GetMapping("/movies")
    fun allMovies(): List<Movie?>? {
        return movieRepository.findAll()
    }
}



