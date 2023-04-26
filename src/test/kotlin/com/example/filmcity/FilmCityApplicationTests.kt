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
    classes = arrayOf(com.example.filmcity::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class com.example.filmcity(@Autowired val mockMvc: MockMvc) {

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
        val coders: List<Coder> = coderRepository.findAll()
        assertThat(
            coders, contains(
                allOf(
                    hasProperty("name", `is`("Laura")),
                    hasProperty("favouriteLanguage", `is`("PHP"))
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `allows to find a coder by id`() {
        val coder: Coder = coderRepository.save(Coder("Marta", "Kotlin"))
        mockMvc.perform(get("/coders/" + coder.id))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", equalTo("Marta")))
            .andExpect(jsonPath("$.favouriteLanguage", equalTo("Kotlin")))
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to get a coder that does not exist`() {
        mockMvc.perform(get("/coders/1"))
            .andExpect(status().isNotFound())
    }

    @Test
    @Throws(Exception::class)
    fun `allows to delete a coder by id`() {
        val coder: Coder = coderRepository.save(Coder("Marta", "Kotlin"))
        mockMvc.perform(delete("/coders/" + coder.id))
            .andExpect(status().isOk)
        val coders: List<Coder> = coderRepository.findAll()
        assertThat(
            coders, not(
                contains(
                    allOf(
                        hasProperty("name", `is`("Marta")),
                        hasProperty("favouriteLanguage", `is`("Kotlin"))
                    )
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error if trying to delete a coder that does not exist`() {
        mockMvc.perform(delete("/coders/1"))
            .andExpect(status().isNotFound())
    }


    @Test
    @Throws(Exception::class)
    fun `allows to modify a coder`() {
        val coder: Coder = coderRepository.save(Coder("Yeraldin", "Java"))
        mockMvc.perform(
            put("/coders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + coder.id + "\", \"name\": \"Yeraldin\", \"favouriteLanguage\": \"Ruby\" }")
        ).andExpect(status().isOk)
        val coders: List<Coder> = coderRepository.findAll()
        assertThat(coders, hasSize(1))
        assertThat(coders[0].name, equalTo("Yeraldin"))
        assertThat(coders[0].favouriteLanguage, equalTo("Ruby"))
    }

    @Test
    @Throws(Exception::class)
    fun `returns an error when trying to modify a coder that does not exist`() {
        addTestCoders()
        mockMvc.perform(
            put("/coders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + -1 + "\", \"name\": \"Pepita\", \"favouriteLanguage\": \"C++\" }")
        ).andExpect(status().isNotFound())
    }

    private fun addTestCoders() {
        val coders: List<Coder> = listOf(
            Coder("Yeraldin", "Java"),
            Coder("Marta", "Kotlin"),
            Coder("Daniela", "Python")
        )
        coders.forEach(coderRepository::save)
    }


}
