package org.example.films.RestControllers;

import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.UserRepository;
import org.example.films.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public String createUser(@RequestBody UserEntity user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "Username cant be empty";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password cant be empty";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "EMAIL cant be empty";
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userService.createUser(user);
        return "User created";
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
    public ResponseEntity<String> editUser(@PathVariable("id") Integer id, @RequestBody UserEntity userinput) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        UserEntity userExist = optionalUser.get();

        if (userinput.getPassword() == null || userinput.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password can't be empty");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userinput.getPassword());
        userExist.setPassword(hashedPassword);

        if (userinput.getEmail() != null && !userExist.getEmail().equals(userinput.getEmail())) {
            if (userRepository.findByEmail(userinput.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email already exists!");
            }
            userExist.setEmail(userinput.getEmail());
        }

        if (userinput.getProfilepic() != null) {
            userExist.setProfilepic(userinput.getProfilepic());
        }

        userRepository.save(userExist);

        return ResponseEntity.ok("User updated successfully");
    }
}
