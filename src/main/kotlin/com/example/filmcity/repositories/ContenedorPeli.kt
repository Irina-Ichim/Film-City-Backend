package com.example.filmcity.repositories

import javax.persistence.*




@Table(name = "Peliculas")
@Entity
data class ContenedorPeli(
        val titulo: String,
        val director: String,
        val releaseYear: Int,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null
)










