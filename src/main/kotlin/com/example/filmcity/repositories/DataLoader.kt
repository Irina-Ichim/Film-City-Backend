package com.example.filmcity.repositories

import com.example.filmcity.repositories.com.example.filmcity.repositories.ContenedorPeli
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct



@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val peliculas = listOf(
                ContenedorPeli(title = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993),
                ContenedorPeli(title = "Ratatouille", director = "Brad Bird", releaseYear = 2007)
        )
        movieRepository.saveAll(peliculas)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $peliculas")
    }
}
