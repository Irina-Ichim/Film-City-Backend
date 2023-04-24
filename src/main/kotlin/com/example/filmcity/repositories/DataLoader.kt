package com.example.filmcity.repositories.com.example.filmcity.repositories

import com.example.filmcity.repositories.MovieRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


  @Component
  class DataLoader(private val movieRepository: MovieRepository) : CommandLineRunner {

      override fun run(vararg args: String?) {
          val movies = listOf(
                  Movie(title = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993),
                  Movie(title = "Ratatouille", director = "Brad Bird", releaseYear = 2007)
          )
          movieRepository.saveAll(movies)
      }
  }

