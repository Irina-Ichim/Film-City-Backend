import com.example.filmcity.repositories.com.example.filmcity.repositories.ContenedorPeli
import com.example.filmcity.repositories.MovieRepository
import org.springframework.web.bind.annotation.*


@RestController
class MovieController(private val movieRepository: MovieRepository) {

    @GetMapping("/Peliculas")
    fun getMovies(): List<ContenedorPeli> {
        return movieRepository.findAll()
    }




    @PostMapping
    fun addMovie(@RequestBody contenedorPeli: ContenedorPeli): ContenedorPeli {
        return movieRepository.save(contenedorPeli)
    }

    @PostMapping("/jurassic-park")
    fun addJurassicPark(): ContenedorPeli {
        val jurassicPark = ContenedorPeli(
            title = "Jurassic Park",
            director = "Steven Spielberg",
            releaseYear = 1993
        )
        return movieRepository.save(jurassicPark)
    }

    @PostMapping("/ratatouille")
    fun addRatatouille(): ContenedorPeli {
        val ratatouille = ContenedorPeli(
            title = "Ratatouille",
            director = "Brad Bird",
            releaseYear = 2007
        )
        return movieRepository.save(ratatouille)
    }
}





