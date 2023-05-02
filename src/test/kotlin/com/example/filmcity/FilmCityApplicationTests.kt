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
    classes = [FilmCityApplicationTests::class],
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
            .andExpect(jsonPath("$[1].director", equalTo("Steven Spielberg")))
            .andExpect(jsonPath("$[1].releaseYear", equalTo(1993)))
            .andExpect(jsonPath("$[2].synopsis", equalTo("Blablablabla")))
            .andDo(print())
    }

    private fun addTestMovies() {
        val movies: List<ContenedorPeli> = listOf(
            ContenedorPeli("Jurassic Park", "Steven Spielberg", 1993, "Blablablabla"),
            ContenedorPeli("Ratatouille", "Brad Bird",2007, "Blablablabla"),
        )
        movies.forEach(movieRepository::save)
    }
}
