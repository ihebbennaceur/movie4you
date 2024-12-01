package org.example.films.Services;

import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public CinemaEntity createCinema(CinemaEntity cinemaEntity) {
        return cinemaRepository.save(cinemaEntity);
    }

    public List<CinemaEntity> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public CinemaEntity getCinemaById(int id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    public void deleteCinemaById(int id) {
        if (cinemaRepository.existsById(id)) {
            cinemaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cinema not found");
        }
    }
}
