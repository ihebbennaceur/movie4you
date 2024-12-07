package org.example.films.Services;

import org.example.films.DTO.UserDTO;
import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public UserEntity addMovieToWatchList(int userId, int movieId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getWatchList().contains(movieId)) {
            user.getWatchList().add(movieId);
        }
        return userRepository.save(user);
    }

    public UserEntity removeMovieFromWatchList(int userId, int movieId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getWatchList().remove(Integer.valueOf(movieId));
        return userRepository.save(user);
    }

    public List<Integer> getWatchList(int userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getWatchList();
    }

    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setProfilepic(userDTO.getProfilepic());
        return userEntity;
    }
}
