package com.example.filmcity.repositories.com.example.filmcity.repositories

import javax.persistence.*

@Entity
@Table(name = "movies")
data class Movie(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val director: String,
        @Column(name = "release_year")
        val releaseYear: Int
)










