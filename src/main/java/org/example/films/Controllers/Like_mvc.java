package org.example.films.Controllers;

import org.example.films.Entitys.LikeEntity;
import org.example.films.Services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Like_mvc {

    @Autowired
     LikeService likeService;


    @PostMapping("/like")
    public String likeReview(@RequestParam int userId, @RequestParam int reviewId,  @RequestParam int movieId,Model model) {
        try {
            LikeEntity like = likeService.likeReview(userId, reviewId);
            model.addAttribute("message", "Like ajouté avec succès !");
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/movie/movie/" + movieId;
    }

    @PostMapping("/unlike")
    public String unlikeReview(@RequestParam int userId, @RequestParam int reviewId,@RequestParam int movieId, Model model) {
        try {
            likeService.unlikeReview(userId, reviewId);
            model.addAttribute("message", "Like supprimé avec succès !");
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/movie/movie/" + movieId;
    }
}
