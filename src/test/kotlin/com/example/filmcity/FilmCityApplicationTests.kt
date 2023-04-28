package com.example.filmcity

import com.example.filmcity.repositories.ContenedorPeli
import com.example.filmcity.repositories.MovieRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(
    classes = arrayOf(FilmCityApplicationTests::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FilmCityApplicationTests(@Autowired val mockMvc: MockMvc) {

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @AfterEach
    fun tearDown() {
        movieRepository.deleteAll()
    }

    @Test
    @Throws(Exception::class)
    fun `returns the existing movies`() {
        addTestMovies()
        mockMvc.perform(get("/peliculas"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[*]", hasSize<Int>(5)))
            .andExpect(jsonPath("$[0].titulo", equalTo("Jurassic Park")))
            //.andExpect(jsonPath("$[0].coverImage", equalTo("Java")))
            .andExpect(jsonPath("$[1].director", equalTo("Steven Spielberg")))
            .andExpect(jsonPath("$[1].releaseYear", equalTo(1993)))
            .andExpect(jsonPath("$[2].synopsis", equalTo("Blablablabla")))
            .andDo(print())
    }

    @Test
    @Throws(Exception::class)
    fun `allows to create a new movie`() {
        mockMvc.perform(
            post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\": \"Jurassic Park\", \"director\": \"Steven Spielberg\",\"releaseYear\": \"1993\" , \"synopsis\": \"Blablablabla\"}")
        ).andExpect(status().isOk)
        val movies: List<ContenedorPeli> = movieRepository.findAll()
        assertThat(
            movies, contains(
                allOf(
                    hasProperty("titulo", `is`("Jurassic Park")),
                    hasProperty("director", `is`("Steven Spielberg"))
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `allows to find a coder by id`() {
        val movie: ContenedorPeli = movieRepository.save(ContenedorPeli("Jurassic Park", "Steven Spielberg", 1993, "Blablablabla"))
        mockMvc.perform(get("/peliculas/" + movie.id))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.titulo", equalTo("Jurassic Park")))
            .andExpect(jsonPath("$.director", equalTo("Steven Spielberg")))
            .andExpect(jsonPath("$.releaseYear", equalTo(1993)))
            .andExpect(jsonPath("$.synopsis", equalTo("Blablablabla")))
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to get a coder that does not exist`() {
        mockMvc.perform(get("/peliculas/1"))
            .andExpect(status().isNotFound())
    }

    @Test
    @Throws(Exception::class)
    fun `allows to delete a coder by id`() {
        val movie: ContenedorPeli = movieRepository.save(ContenedorPeli("Jurassic Park", "Steven Spielberg", 1993, "Blablablabla"))
        mockMvc.perform(delete("/peliculas/" + movie.id))
            .andExpect(status().isOk)
        val movies: List<ContenedorPeli> = movieRepository.findAll()
        assertThat(
            movies, not(
                contains(
                    allOf(
                        hasProperty("titulo", `is`("Jurassic Park")),
                        hasProperty("director", `is`("Steven Spielberg"))

                    )
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to delete a coder that does not exist`() {
        mockMvc.perform(delete("/peliculas/1"))
            .andExpect(status().isNotFound())
    }


    @Test
    @Throws(Exception::class)
    fun `allows to modify a movie`() {
        val movie: ContenedorPeli = movieRepository.save(ContenedorPeli("Jurassic Park", "Steven Spielberg", 1993, "Blablablabla"))
        mockMvc.perform(
            put("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + movie.id + "\", \"titulo\": \"Jurassic Park\", \"director\": \"Steven Spielberg\" }")
        ).andExpect(status().isOk)
        val movies: List<ContenedorPeli> = movieRepository.findAll()
        assertThat(movies, hasSize(1))
        assertThat(movies[0].titulo, equalTo("Jurassic Park"))
        assertThat(movies[0].director, equalTo("Steven Spielberg"))
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error when trying to modify a coder that does not exist`() {
        addTestMovies()
        mockMvc.perform(
            put("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + -1 + "\", \"titulo\": \"Jurassic Park\", \"director\": \"Steven Spielberg\" }")
        ).andExpect(status().isNotFound())
    }

    private fun addTestMovies() {
        val movies: List<ContenedorPeli> = listOf(
            ContenedorPeli("Jurassic Park", "Steven Spielberg", 1993, "Blablablabla"),
            ContenedorPeli("Ratatouille", "Brad Bird",2007, "Blablablabla"),
           // Coder("Daniela", "Python")
        )
        movies.forEach(movieRepository::save)
    }


}
