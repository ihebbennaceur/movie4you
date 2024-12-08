package org.example.films.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.films.Entitys.SignalementEntity;
import org.example.films.Repositories.SignalementRepository;
import org.example.films.Services.SignalementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/signalement")
public class Signalement_mvc {

    @Autowired
    private SignalementService signalementService;

    @Autowired
    private SignalementRepository signalementRepository;

    @GetMapping("/all_signalements")
    public String getAllSignalements(Model model) {
        List<SignalementEntity> signalements = signalementService.getAllSignalements();
        model.addAttribute("signalements", signalements);
        return "signalement/list";
    }

    @GetMapping("/create_signalement")
    public String showCreateSignalementForm(Model model) {
        model.addAttribute("signalement", new SignalementEntity());
        return "signalement/create";
    }

    @PostMapping("/create_signalement")
    public String createSignalement(
            @ModelAttribute SignalementEntity signalement,
            @RequestParam int userId,
            @RequestParam int reviewId, HttpSession session,
            Model model
    ) {
        try {
            SignalementEntity newSignalement = signalementService.createSignalement(userId, reviewId, signalement.getRaison());
            return "redirect:/movie/all_movies";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signalement/create";
        }
    }

    @GetMapping("/signalement/{id}")
    public String showSignalementDetails(@PathVariable("id") Integer id, Model model) {
        Optional<SignalementEntity> signalementOptional = signalementRepository.findById(id);
        if (signalementOptional.isPresent()) {
            model.addAttribute("signalement", signalementOptional.get());
            return "signalement/details";
        }
        model.addAttribute("error", "Signalement not found!");
        return "error/404";
    }



    @GetMapping("/edit_signalement/{id}")
    public String showEditSignalementForm(@PathVariable("id") Integer id, Model model) {
        Optional<SignalementEntity> signalement = signalementRepository.findById(id);
        if (!signalement.isPresent()) {
            return "error/404";
        }
        model.addAttribute("signalement", signalement.get());
        return "signalement/edit";
    }

    @PostMapping("/edit_signalement/{id}")
    public String editSignalement(
            @PathVariable("id") Integer id,
            @ModelAttribute SignalementEntity signalementInput
    ) {
        Optional<SignalementEntity> optionalSignalement = signalementRepository.findById(id);
        if (!optionalSignalement.isPresent()) {
            return "error/404";
        }

        SignalementEntity existingSignalement = optionalSignalement.get();
        if (signalementInput.getStatus() != null) {
            existingSignalement.setStatus(signalementInput.getStatus());
        }

        signalementRepository.save(existingSignalement);
        return "redirect:/signalement/all_signalements";
    }

    @GetMapping("/delete_signalement/{id}")
    public String deleteSignalement(@PathVariable("id") Integer id, Model model) {
        if (!signalementRepository.existsById(id)) {
            model.addAttribute("error", "Signalement not found!");
            return "error/404";
        }

        signalementService.deleteSignalement(id);
        return "redirect:/signalement/all_signalements";
    }
}
