package com.example.filmcity.repositories

import jakarta.persistence.*

@Table(name = "movies")
@Entity

data class Movie(
        var title: String,
        var coverImage: String,
        var director: String,
        var year: Int,
        var synopsis: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null
)




