package org.example.films.Controllers;

import org.example.films.DTO.CinemaDTO;
import org.example.films.Entitys.CinemaEntity;
import org.example.films.Repositories.CinemaRepository;
import org.example.films.Services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cinema")
public class Cinema_mvc {

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping("/all")
    public String getAllCinemas(Model model) {
        List<CinemaEntity> cinemas = cinemaService.getAllCinemas();
        model.addAttribute("cinemas", cinemas);
        return "cinema/cinemas";
    }

    @GetMapping("/details/{id}")
    public String getCinemaDetails(@PathVariable int id, Model model) {
        CinemaEntity cinema = cinemaService.getAllCinemas().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("cinema", cinema);
        return "cinema/cinema-details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cinemaDTO", new CinemaDTO());
        return "cinema/create-cinema";
    }

    @PostMapping("/create")
    public String createCinema(@ModelAttribute CinemaDTO cinemaDTO) {
        CinemaEntity cinema = new CinemaEntity();
        cinema.setName(cinemaDTO.getName());
        cinema.setLocation(cinemaDTO.getLocation());
        cinema.setCapacity(cinemaDTO.getCapacity());
        cinema.setContactInfo(cinemaDTO.getContactInfo());
        cinemaService.addCinema(cinema);
        return "redirect:/cinema/all";
    }


    @GetMapping("/delete/{id}")
    public String deleteCinema(@PathVariable  Integer id) {
        cinemaService.deleteCinemaById(id);
        return "redirect:/cinema/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<CinemaEntity> cinemaOptional = cinemaRepository.findById(id);
        if (!cinemaOptional.isPresent()) {
            return "error/404";
        }
        model.addAttribute("cinema", cinemaOptional.get());
        return "cinema/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCinema(@PathVariable("id") int id, @ModelAttribute CinemaEntity cinemaInput, Model model) {
        Optional<CinemaEntity> optionalCinema = cinemaRepository.findById(id);
        if (!optionalCinema.isPresent()) {
            model.addAttribute("error", "Cinema not found!");
            return "error/404";
        }

        CinemaEntity existingCinema = optionalCinema.get();
        updateCinemaFields(existingCinema, cinemaInput);
        cinemaRepository.save(existingCinema);

        return "redirect:/cinema/all";
    }

    private void updateCinemaFields(CinemaEntity existingCinema, CinemaEntity cinemaInput) {
        if (cinemaInput.getName() != null && !cinemaInput.getName().trim().isEmpty())
            existingCinema.setName(cinemaInput.getName());
        if (cinemaInput.getLocation() != null && !cinemaInput.getLocation().trim().isEmpty())
            existingCinema.setLocation(cinemaInput.getLocation());
        if (cinemaInput.getCapacity() != null)
            existingCinema.setCapacity(cinemaInput.getCapacity());
        if (cinemaInput.getContactInfo() != null && !cinemaInput.getContactInfo().trim().isEmpty())
            existingCinema.setContactInfo(cinemaInput.getContactInfo());
    }
}

