package org.example.films.Services;

import org.example.films.DTO.SeanceDTO;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.SeanceRepository;
import org.example.films.Repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final CinemaRepository cinemaRepository;
    private static final Logger logger = Logger.getLogger(SeanceService.class.getName());

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, CinemaRepository cinemaRepository) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public SeanceEntity addSeance(SeanceDTO seanceDTO) {
        logger.info("Creating a new seance with movie title: " + seanceDTO.getMovieTitle());

        Optional<CinemaEntity> cinemaOptional = cinemaRepository.findById(seanceDTO.getCinemaId());
        if (cinemaOptional.isEmpty()) {
            String errorMessage = "Cinema with ID " + seanceDTO.getCinemaId() + " not found.";
            logger.severe(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        CinemaEntity cinema = cinemaOptional.get();
        logger.info("Found cinema with ID: " + cinema.getId());

        SeanceEntity seance = new SeanceEntity();
        seance.setMovieTitle(seanceDTO.getMovieTitle());
        seance.setStartTime(seanceDTO.getStartTime());
        seance.setDuration(seanceDTO.getDuration());
        seance.setCinema(cinema);
        seance.setDate(seanceDTO.getStartTime().toLocalDate()); // Assurez-vous de définir la date

        try {
            SeanceEntity savedSeance = seanceRepository.save(seance);
            logger.info("Seance created successfully with ID: " + savedSeance.getId());
            return savedSeance;
        } catch (Exception e) {
            String errorMessage = "Error occurred while saving the seance: " + e.getMessage();
            logger.severe(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }


    public List<SeanceEntity> getSeancesByCinemaId(int cinemaId) {
        return seanceRepository.findByCinemaId(cinemaId);
    }

    public void deleteSeanceById(int id) {
        seanceRepository.deleteById(id);
    }
}
