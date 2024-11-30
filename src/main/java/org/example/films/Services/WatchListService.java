//package org.example.films.Services;
//
//import org.example.films.Entitys.MovieEntity;
//import org.example.films.Entitys.UserEntity;
//import org.example.films.Repositories.MovieRepository;
//import org.example.films.Repositories.UserRepository;
//import org.example.films.Repositories.WatchListRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WatchListService {
//
//    @Autowired
//    private WatchListRepository watchListRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MovieRepository movieRepository;
//
//    public WatchList addMovieToWatchList(int userId, int movieId) {
//        UserEntity user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        MovieEntity movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new RuntimeException("Movie not found"));
//
//        WatchList watchList = new WatchList();
//        watchList.setUser(user);
//        watchList.setMovie(movie);
//        return watchListRepository.save(watchList);
//    }
//
//    public void removeMovieFromWatchList(int userId, int movieId) {
//        watchListRepository.deleteByUserIdAndMovieId(userId, movieId);
//    }
//}
