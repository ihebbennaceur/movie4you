package org.example.films.RestControllers;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Entitys.MovieEntity;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Repositories.SeanceRepository;
import org.example.films.Services.CinemaService;
import org.example.films.Services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/seances")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping
    public List<SeanceEntity> getAllSeances() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    public SeanceEntity getSeanceById(@PathVariable int id) {
        return seanceService.getSeanceById(id);
    }

    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeanceRepository seanceRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createSeance(@RequestBody SeanceEntity seance) {
        try {
            // Vérifier si le cinéma et le film existent
            Optional<CinemaEntity> cinema = cinemaRepository.findById(seance.getCinema().getId());
            Optional<MovieEntity> movie = movieRepository.findById(seance.getMovie().getId());

            if (cinema.isEmpty() || movie.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cinema or Movie not found");
            }

            // Logique pour enregistrer la séance
            SeanceEntity savedSeance = seanceRepository.save(seance);

            return ResponseEntity.ok(savedSeance);
        } catch (Exception e) {
            // Capture des erreurs potentielles et journalisation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
//    @PostMapping
//    public SeanceEntity saveSeance(@RequestBody SeanceEntity seance) {
//        return seanceService.saveSeance(seance);
//    }

    @DeleteMapping("/{id}")
    public void deleteSeance(@PathVariable int id) {
        seanceService.deleteSeance(id);
    }
}
