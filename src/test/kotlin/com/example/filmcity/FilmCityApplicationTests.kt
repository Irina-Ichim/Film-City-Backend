package com.example.filmcity

import com.example.filmcity.repositories.ContenedorPeli
import com.example.filmcity.repositories.MovieRepository
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class FilmCityApplicationTests(@Autowired val mockMvc: MockMvc) {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @AfterEach
    fun tearDown() {
        movieRepository.deleteAll()
    }
    @BeforeEach
    fun setUp() {
        // Eliminamos todas las películas antes de cada prueba
        movieRepository.deleteAll()
    }
    private fun Any.toJson(): String {
        return com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(this)
    }

    @Test
    @Throws(Exception::class)
    fun `adds a new movie using POST request`() {
        val newMovie = ContenedorPeli(
            titulo = "Inception",
            director = "Christopher Nolan",
            releaseYear = 2010,
            synopsis = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."
        )

        mockMvc.perform(
            post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newMovie.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.titulo", equalTo("Inception")))
            .andExpect(jsonPath("$.director", equalTo("Christopher Nolan")))
            .andExpect(jsonPath("$.releaseYear", equalTo(2010)))
            .andExpect(
                jsonPath(
                    "$.synopsis",
                    equalTo("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.")
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun `returns the existing movies using GET request`() {
        val peliculas = listOf(
            ContenedorPeli(
                titulo = "Jurassic Park",
                director = "Steven Spielberg",
                releaseYear = 1993,
                synopsis = "Blablablabla"
            ),
            ContenedorPeli(
                titulo = "Ratatouille",
                director = "Brad Bird",
                releaseYear = 2007,
                synopsis = "Blablablabla"
            )
        )

        movieRepository.saveAll(peliculas)

        mockMvc.perform(get("/peliculas"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", hasSize<Int>(2)))
            .andExpect(jsonPath("$[0].titulo", equalTo("Jurassic Park")))
            .andExpect(jsonPath("$[0].director", equalTo("Steven Spielberg")))
            .andExpect(jsonPath("$[0].releaseYear", equalTo(1993)))
            .andExpect(jsonPath("$[0].synopsis", equalTo("Blablablabla")))
            .andExpect(jsonPath("$[1].titulo", equalTo("Ratatouille")))
            .andExpect(jsonPath("$[1].director", equalTo("Brad Bird")))
            .andExpect(jsonPath("$[1].releaseYear", equalTo(2007)))
            .andExpect(jsonPath("$[1].synopsis", equalTo("Blablablabla")))
    }

    @Test
    @Throws(Exception::class)
    fun `returns a movie by ID using GET request`() {
        val pelicula = ContenedorPeli(
            titulo = "The Matrix",
            director = "Lana Wachowski",
            releaseYear = 1999,
            synopsis = "A computer programmer discovers a mysterious world within the Matrix, a simulated reality created by sentient machines."
        )

        // Guardar la película en la base de datos y obtener su ID
        val savedPelicula = movieRepository.save(pelicula)

        mockMvc.perform(get("/peliculas/${savedPelicula.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.titulo", equalTo("The Matrix")))
            .andExpect(jsonPath("$.director", equalTo("Lana Wachowski")))
            .andExpect(jsonPath("$.releaseYear", equalTo(1999)))
            .andExpect(
                jsonPath(
                    "$.synopsis",
                    equalTo("A computer programmer discovers a mysterious world within the Matrix, a simulated reality created by sentient machines.")
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun `updates a movie using PUT request`() {
        // Creamos una película para probar la actualización
        val newMovie = ContenedorPeli(
            titulo = "Inception",
            director = "Christopher Nolan",
            releaseYear = 2010,
            synopsis = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."
        )

        // Guardamos la película en la base de datos y obtenemos su ID
        val savedPelicula = movieRepository.save(newMovie)

        // Película actualizada
        val updatedPelicula = ContenedorPeli(
            id = savedPelicula.id,
            titulo = "Inception Reloaded",
            director = savedPelicula.director,
            releaseYear = savedPelicula.releaseYear,
            synopsis = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O. - Reloaded"
        )

        mockMvc.perform(
            put("/peliculas/${savedPelicula.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPelicula.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.titulo", equalTo("Inception Reloaded")))
            .andExpect(jsonPath("$.director", equalTo("Christopher Nolan")))
            .andExpect(jsonPath("$.releaseYear", equalTo(2010)))
            .andExpect(
                jsonPath(
                    "$.synopsis",
                    equalTo("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O. - Reloaded")
                )
            )
    }
}