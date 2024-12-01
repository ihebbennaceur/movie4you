package org.example.films.Services;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Entitys.MovieEntity;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Repositories.MovieRepository;
import org.example.films.Repositories.SeanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    public SeanceService(SeanceRepository seanceRepository, CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    public List<SeanceEntity> getAllSeances() {
        return seanceRepository.findAll();
    }

    public SeanceEntity getSeanceById(int id) {
        return seanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Seance not found"));
    }

    public SeanceEntity saveSeance(SeanceEntity seance) {
        if (seance.getCinema() != null) {
            CinemaEntity cinema = cinemaRepository.findById(seance.getCinema().getId()).orElse(null);
            if (cinema != null) {
                seance.setCinema(cinema);
            } else {
                // Gérez le cas où le cinéma est introuvable, par exemple, lancer une exception ou affecter une valeur par défaut
                throw new RuntimeException("Cinema not found");
            }
        }

        if (seance.getMovie() != null) {
            MovieEntity movie = movieRepository.findById(seance.getMovie().getId()).orElse(null);
            if (movie != null) {
                seance.setMovie(movie);
            } else {
                // Gérez le cas où le film est introuvable
                throw new RuntimeException("Movie not found");
            }
        }

        return seanceRepository.save(seance);
    }


    public void deleteSeance(int id) {
        seanceRepository.deleteById(id);
    }
}
