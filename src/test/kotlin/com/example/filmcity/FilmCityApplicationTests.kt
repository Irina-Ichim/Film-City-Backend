package com.example.filmcity


import com.example.filmcity.repositories.ContenedorPeli
import com.example.filmcity.repositories.DataLoader
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
    classes = arrayOf(FilmCityApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FilmCityApplicationTests(@Autowired val mockMvc: MockMvc) {

    @Autowired
    private lateinit var dataLoader: DataLoader

    @Autowired
    private lateinit var movieRepository: MovieRepository


    @AfterEach
    fun tearDown() {
        movieRepository.deleteAll()
    }

    fun addTestMovies() {
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
    }

    @Test
    @Throws(Exception::class)
    fun `returns the existing movies`() {
        addTestMovies()
        mockMvc.perform(get("/peliculas"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[*]", hasSize<Int>(4)))
            .andExpect(jsonPath("$[0].titulo", equalTo("Jurassic Park")))
            .andExpect(jsonPath("$[0].director", equalTo("Steven Spielberg")))
            .andExpect(jsonPath("$[0].releaseYear", equalTo(1993)))
            .andExpect(jsonPath("$[0].synopsis", equalTo("Blablablabla")))
            .andExpect(jsonPath("$[1].titulo", equalTo("Ratatouille")))
            .andExpect(jsonPath("$[1].director", equalTo("Brad Bird")))
            .andExpect(jsonPath("$[1].releaseYear", equalTo(2007)))
            .andExpect(jsonPath("$[1].synopsis", equalTo("Blablablabla")))
            .andDo(print())
    }
}


