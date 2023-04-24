import com.example.filmcity.repositories.com.example.filmcity.repositories.Movie
import com.example.filmcity.repositories.MovieRepository
import org.springframework.web.bind.annotation.*


    @RestController
    class MovieController(private val movieRepository: MovieRepository) {

        @GetMapping("")
        fun getMovies(): List<Movie> {
            return movieRepository.findAll()
        }

        @PostMapping
        fun addMovie(@RequestBody movie: Movie): Movie {
            return movieRepository.save(movie)
        }

        @PostMapping("/jurassic-park")
        fun addJurassicPark(): Movie {
            val jurassicPark = Movie(
                    title = "Jurassic Park",
                    director = "Steven Spielberg",
                    releaseYear = 1993
            )
            return movieRepository.save(jurassicPark)
        }

        @PostMapping("/ratatouille")
        fun addRatatouille(): Movie {
            val ratatouille = Movie(
                    title = "Ratatouille",
                    director = "Brad Bird",
                    releaseYear = 2007
            )
            return movieRepository.save(ratatouille)
        }
    }





