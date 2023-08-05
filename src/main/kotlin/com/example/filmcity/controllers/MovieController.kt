package com.example.filmcity.controllers

import com.example.filmcity.repositories.ContenedorPeli
import com.example.filmcity.repositories.MovieRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MovieController(private val movieRepository: MovieRepository) {

    @GetMapping("/peliculas")
    fun allMovies(): List<ContenedorPeli?>? {
        return movieRepository.findAll()
    }

    @PostMapping("/peliculas")
    fun addMovie(@RequestBody contenedorPeli: ContenedorPeli): ContenedorPeli? {
        return movieRepository.save(contenedorPeli)
    }

    @GetMapping("/peliculas/{id}")
    fun findMovie(@PathVariable id: Long): ContenedorPeli? {
        return movieRepository.findById(id).orElseThrow { MovieNotFoundException() }
    }

    @DeleteMapping("/peliculas/{id}")
    fun deleteMovieById(@PathVariable id: Long?): ContenedorPeli? {
        id?.let {
            val movie: ContenedorPeli = movieRepository.findById(it).orElseThrow { MovieNotFoundException() }
            movieRepository.deleteById(it)
            return movie
        }
        return null
    }


    @PutMapping("/peliculas/{id}")
    fun updateMovieById(@PathVariable id: Long, @RequestBody contenedorPeli: ContenedorPeli): ContenedorPeli? {
        contenedorPeli.id = id // Establecer el ID en el objeto contenedorPeli recibido del cuerpo de la solicitud
        if (movieRepository.existsById(id)) {
            return movieRepository.save(contenedorPeli)
        } else {
            throw MovieNotFoundException()
        }
    }

}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "movie not found")
class MovieNotFoundException : RuntimeException()


