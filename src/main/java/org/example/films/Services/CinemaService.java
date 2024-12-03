package org.example.films.Services;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<CinemaEntity> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public CinemaEntity getCinemaById(int id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    public CinemaEntity saveCinema(CinemaEntity cinema) {
        return cinemaRepository.save(cinema);
    }

    public void deleteCinema(int id) {
        cinemaRepository.deleteById(id);
    }
}
