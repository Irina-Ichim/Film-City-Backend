package com.example.filmcity.repositories

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component



@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val peliculas = listOf(
            ContenedorPeli(titulo = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993, synopsis = "Blablablabla"),
            ContenedorPeli(titulo = "Ratatouille", director = "Brad Bird", releaseYear = 2007, synopsis = "Blablablabla")
        )
        movieRepository.saveAll(peliculas)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $peliculas")
    }
}
