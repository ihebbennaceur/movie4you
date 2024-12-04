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

    public CinemaEntity addCinema(CinemaEntity cinema) {
        return cinemaRepository.save(cinema);
    }

    public List<CinemaEntity> getCinemasByName(String name) {
        return cinemaRepository.findByNameContaining(name);
    }

    public List<CinemaEntity> getCinemasByLocation(String location) {
        return cinemaRepository.findByLocation(location);
    }

    public List<CinemaEntity> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public void deleteCinemaById(int id) {
        cinemaRepository.deleteById(id);
    }
}
