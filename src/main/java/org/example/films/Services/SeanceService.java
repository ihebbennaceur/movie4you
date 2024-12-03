

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

    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public List<SeanceEntity> getAllSeances() {
        return seanceRepository.findAll();
    }

    public SeanceEntity getSeanceById(int id) {
        return seanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Seance not found"));
    }

    CinemaRepository cinemaRepository;
    MovieRepository movieRepository;
    public SeanceEntity saveSeance(SeanceEntity seance) {
        // Assurez-vous que l'objet 'cinema' et 'movie' est bien référencé
        // Si nécessaire, chargez l'entité existante depuis la base de données avant de l'assigner
        if (seance.getCinema() != null) {
            // Exemple : charge l'entité cinéma à partir de l'ID (si nécessaire)
            CinemaEntity cinema = cinemaRepository.findById(seance.getCinema().getId()).orElse(null);
            seance.setCinema(cinema);
        }
        if (seance.getMovie() != null) {
            // Exemple : charge l'entité film à partir de l'ID (si nécessaire)
            MovieEntity movie = movieRepository.findById(seance.getMovie().getId()).orElse(null);
            seance.setMovie(movie);
        }
        return seanceRepository.save(seance);
    }


    public void deleteSeance(int id) {
        seanceRepository.deleteById(id);
    }
}
