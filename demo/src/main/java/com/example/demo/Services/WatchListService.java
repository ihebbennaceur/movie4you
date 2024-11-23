package com.example.demo.Services;

import com.example.demo.Entitys.MovieEntity;
import com.example.demo.Entitys.UserEntity;
import com.example.demo.Entitys.WatchList;
import com.example.demo.Repositories.MovieRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Repositories.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;


    public WatchList addMovieToWatchList(Integer userId, Integer movieId) {

        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<MovieEntity> movieOpt = movieRepository.findById(movieId);

        if (userOpt.isPresent() && movieOpt.isPresent()) {

            Optional<WatchList> existingWatchList = watchListRepository
                    .findByUserAndMovie(userOpt.get(), movieOpt.get());

            if (existingWatchList.isPresent()) {
                return null;  //si il est la on ajoute rien
            }


            WatchList newWatchList = new WatchList();
            newWatchList.setUser(userOpt.get());
            newWatchList.setMovie(movieOpt.get());
            newWatchList.setWatched(false);
            newWatchList.setAddedAt(LocalDateTime.now());

            return watchListRepository.save(newWatchList);
        }
        return null;  //si no user no film
    }
}
