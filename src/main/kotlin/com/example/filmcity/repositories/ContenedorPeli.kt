package com.example.filmcity.repositories



@Table(name = "Peliculas")
@Entity
data class ContenedorPeli(
        val titulo: String,
        val director: String,
        val releaseYear: Int,
        val synopsis: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null
)










