package org.example.films.Controllers;

import org.example.films.Entitys.ReviewEntity;
import org.example.films.Services.ReviewService;
import org.example.films.Services.UserService;
import org.example.films.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class Review_mvc {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/add")
    public String showAddReviewForm(Model model) {

        return "reviews/addReview";
    }
    @PostMapping("/add")
    public String addReview(
            @RequestParam int userId,
            @RequestParam int movieId,
            @RequestParam String content,
            Model model) {
        ReviewEntity review = reviewService.addReview(userId, movieId, content);
        model.addAttribute("review", review);
        model.addAttribute("userId", userId);
        return "reviews/reviewAdded";
    }

    @GetMapping("/user/{userId}")
    public String getAllReviewsForUser(@PathVariable int userId, Model model) {
        try {
            List<ReviewEntity> reviews = reviewService.getAllReviewsByUser(userId);
            if (reviews.isEmpty()) {
                model.addAttribute("message", "Aucune critique trouvée pour cet utilisateur.");
            }
            model.addAttribute("reviews", reviews);
            return "reviews/userReviews";
        } catch (RuntimeException e) {
            e.printStackTrace(); // Pour identifier l'erreur
            model.addAttribute("error", "Une erreur inattendue est survenue lors de la récupération des critiques.");
            return "error"; // Page d'erreur personnalisée
        }
    }



    @GetMapping("/delete/{reviewId}")
    public String removeReview(@PathVariable int reviewId, Model model) {
        reviewService.removeReview(reviewId);
        model.addAttribute("message", "Critique supprimée avec succès");
        return "reviews/reviewDeleted";
    }
}