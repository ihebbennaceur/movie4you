package org.example.films.Services;

import org.example.films.DTO.SeanceDTO;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Repositories.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final CinemaRepository cinemaRepository;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, CinemaRepository cinemaRepository) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public SeanceEntity addSeance(SeanceDTO seanceDTO) {
        Optional<CinemaEntity> cinemaOptional = cinemaRepository.findById(seanceDTO.getCinemaId());
        if (!cinemaOptional.isPresent()) {
            throw new RuntimeException("Cinema with ID " + seanceDTO.getCinemaId() + " not found");
        }

        SeanceEntity seance = new SeanceEntity();
        seance.setMovieTitle(seanceDTO.getMovieTitle());
        seance.setStartTime(seanceDTO.getStartTime());
        seance.setDuration(seanceDTO.getDuration());
        seance.setCinema(cinemaOptional.get());

        return seanceRepository.save(seance);
    }
    public List<SeanceEntity> getSeancesByCinemaId(int cinemaId) {
        return seanceRepository.findByCinemaId(cinemaId);
    }

    public void deleteSeanceById(int id) {
        seanceRepository.deleteById(id);
    }
}
