package org.example.films.Controllers;

import org.example.films.DTO.SeanceDTO;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Entitys.SeanceEntity;
import org.example.films.Repositories.SeanceRepository;
import org.example.films.Services.MovieService;
import org.example.films.Services.SeanceService;
import org.example.films.Repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seance")
public class Seance_mvc {

    @Autowired
    private SeanceService seanceService;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping("/all")
    public String getAllSeances(Model model) {
        List<SeanceEntity> seances = seanceRepository.findAll();
        model.addAttribute("seances", seances);
        return "seance/seances";
    }



    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("seanceDTO", new SeanceDTO());
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "seance/create-seance";
    }

    @PostMapping("/create")
    public String createSeance(@ModelAttribute SeanceDTO seanceDTO) {
        seanceService.addSeance(seanceDTO);
        return "redirect:/seance/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<SeanceEntity> seanceOptional = seanceRepository.findById(id);
        if (!seanceOptional.isPresent()) {
            return "error/404"; // Page d'erreur si la séance n'existe pas
        }
        model.addAttribute("seance", seanceOptional.get());
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "seance/edit-seance";
    }

    @PostMapping("/edit/{id}")
    public String editSeance(@PathVariable int id, @ModelAttribute SeanceEntity seanceInput) {
        Optional<SeanceEntity> optionalSeance = seanceRepository.findById(id);
        if (!optionalSeance.isPresent()) {
            return "error/404";
        }
        SeanceEntity existingSeance = optionalSeance.get();
        // Mise à jour des champs de la séance
        existingSeance.setMovieTitle(seanceInput.getMovieTitle());
        existingSeance.setStartTime(seanceInput.getStartTime());
        existingSeance.setDuration(seanceInput.getDuration());
        existingSeance.setCinema(seanceInput.getCinema());
        seanceRepository.save(existingSeance);
        return "redirect:/seance/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeance(@PathVariable int id) {
        seanceService.deleteSeanceById(id);
        return "redirect:/seance/all";
    }
}
