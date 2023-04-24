package com.example.filmcity.repositories

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct



@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val peliculas = listOf(
                ContenedorPeli(titulo = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993),
                ContenedorPeli(titulo = "Ratatouille", director = "Brad Bird", releaseYear = 2007)
        )
        movieRepository.saveAll(peliculas)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $peliculas")
    }
}
