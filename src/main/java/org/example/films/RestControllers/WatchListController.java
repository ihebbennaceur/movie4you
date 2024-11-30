//package org.example.films.RestControllers;
//
//import org.example.films.Services.WatchListService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/watchlist")
//public class WatchListController {
//
//    @Autowired
//    private WatchListService watchListService;
//
//    @PostMapping("/add")
//    public ResponseEntity<WatchList> addMovieToWatchList(
//            @RequestParam int userId,
//            @RequestParam int movieId) {
//        WatchList watchList = watchListService.addMovieToWatchList(userId, movieId);
//        return ResponseEntity.ok(watchList);
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<Void> removeMovieFromWatchList(
//            @RequestParam int userId,
//            @RequestParam int movieId) {
//        watchListService.removeMovieFromWatchList(userId, movieId);
//        return ResponseEntity.noContent().build();
//    }
//}
