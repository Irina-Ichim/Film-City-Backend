

package com.example.filmcity.repositories

import com.example.filmcity.repositories.com.example.filmcity.repositories.ContenedorPeli
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<ContenedorPeli, Long>