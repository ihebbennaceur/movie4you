package org.example.films.RestControllers;

import jakarta.validation.Valid;
import org.example.films.DTO.UserDTO;
import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.UserRepository;
import org.example.films.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}/watchlist/add/{movieId}")
    public ResponseEntity<UserEntity> addMovieToWatchList(
            @PathVariable int userId,
            @PathVariable int movieId) {
        UserEntity user = userService.addMovieToWatchList(userId, movieId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}/watchlist/remove/{movieId}")
    public ResponseEntity<UserEntity> removeMovieFromWatchList(
            @PathVariable int userId,
            @PathVariable int movieId) {
        UserEntity user = userService.removeMovieFromWatchList(userId, movieId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/watchlist")
    public ResponseEntity<List<Integer>> getWatchList(@PathVariable int userId) {
        List<Integer> watchList = userService.getWatchList(userId);
        return ResponseEntity.ok(watchList);
    }

    @GetMapping("/all_users")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        UserEntity userEntity = userService.convertToEntity(userDTO);
        if (userEntity.getPassword() != null && !userEntity.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        }

        userRepository.save(userEntity);
        return ResponseEntity.ok("User created successfully");
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        if (!userRepository.existsById(id)) {
            return "User not found!";
        }

        userService.deleteUserById(id);
        return "User is deleted ";
    }

    @PutMapping("/edit_user/{id}")
    public ResponseEntity<String> editUser(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String profilepic
    ) {
        Optional<UserEntity> userDb = userRepository.findById(id);
        if (!userDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        UserEntity user = userDb.get();

        // Mise à jour des champs si des valeurs ont été passées
        if (username != null) {
            if (userRepository.findByUsername(username) != null) {
                return ResponseEntity.badRequest().body("Username already exists!");
            }
            user.setUsername(username);
        }
        if (email != null) {
            if (userRepository.findByEmail(email) != null) {
                return ResponseEntity.badRequest().body("Email already exists!");
            }
            user.setEmail(email);
        }
        if (password != null && !password.trim().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(password);
            user.setPassword(hashedPassword);
        }
        if (profilepic != null) {
            user.setProfilepic(profilepic);
        }

        userRepository.save(user);
        return ResponseEntity.ok("User updated successfully: " + user.getUsername());
    }
}
