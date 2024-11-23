package com.example.demo.RestControllers;

import com.example.demo.Entitys.WatchList;
import com.example.demo.Services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/watchlist")

public class WatchListController {
    @Autowired
    private WatchListService watchListService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovieToWatchList(@RequestParam Integer userId, @RequestParam Integer movieId) {

        WatchList addedWatchList = watchListService.addMovieToWatchList(userId, movieId);

        if (addedWatchList != null) {
            return ResponseEntity.ok("Movie added to WatchList successfully.");
        } else {
            return ResponseEntity.badRequest().body("Movie already in WatchList or invalid data.");
        }
    }
}
