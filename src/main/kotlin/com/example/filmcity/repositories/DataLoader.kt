package com.example.filmcity.repositories.com.example.filmcity.repositories

import com.example.filmcity.repositories.MovieRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


//@Component
  //class DataLoader(private val movieRepository: MovieRepository) : CommandLineRunner {

   //   override fun run(vararg args: String?) {
  //        var contenedorPelis = listOf(
   //               ContenedorPeli(title = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993),
   //               ContenedorPeli(title = "Ratatouille", director = "Brad Bird", releaseYear = 2007)
   //       )
   //       movieRepository.saveAll(contenedorPelis)
   //   }
  //}


@Component
class DataLoader(private val movieRepository: MovieRepository) {
    @PostConstruct
    fun load() {
        val Peliculas = listOf(
                ContenedorPeli(title = "Jurassic Park", director = "Steven Spielberg", releaseYear = 1993),
                ContenedorPeli(title = "Ratatouille", director = "Brad Bird", releaseYear = 2007)
        )
        movieRepository.saveAll(Peliculas)
        println("Cargamos datos de prueba cuando arrancamos el servidor: $Peliculas")
    }
}
