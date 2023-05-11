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
    fun deleteMovieById(@PathVariable id: Long): ContenedorPeli? {
    val movie: ContenedorPeli = movieRepository.findById(id).orElseThrow {MovieNotFoundException() }
    movieRepository.deleteById(id)
    return movie
        }


    @PutMapping("/peliculas")
    fun updateMovieById(@RequestBody contenedorPeli: ContenedorPeli): ContenedorPeli? {
    contenedorPeli.id?.let { movieRepository.findById(it).orElseThrow { MovieNotFoundException() } }
    return movieRepository.save(contenedorPeli)
        }

    }

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "movie not found")
class MovieNotFoundException : RuntimeException()



