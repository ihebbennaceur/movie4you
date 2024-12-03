package org.example.films.Controllers;

import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.UserRepository;
import org.example.films.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class User_mvc {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all_users")
    public String getAllUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }


    @GetMapping("/create_user")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user/create";
    }

    @PostMapping("/create_user")
    public String createUser(@ModelAttribute UserEntity user, Model model) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            model.addAttribute("error", "Username can't be empty");
            return "user/create";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            model.addAttribute("error", "Password can't be empty");
            return "user/create";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            model.addAttribute("error", "Email can't be empty");
            return "user/create";
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists!");
            return "user/create";
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);


        userService.createUser(user);
        return "redirect:/user/all_users";
    }

    @GetMapping("/user/{id}")
    public String showUserDetails(@PathVariable int id, Model model) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/details";
        } else {
            model.addAttribute("error", "User not found!");
            return "error";
        }
    }

    @GetMapping("/edit_user/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "error/404";
        }
        model.addAttribute("user", user.get());
        return "user/edit";
    }

    @PostMapping("/edit_user/{id}")
    public String editUser(@PathVariable("id") Integer id, @ModelAttribute UserEntity userinput, Model model) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            model.addAttribute("error", "User not found!");
            return "error/404";
        }

        UserEntity userExist = optionalUser.get();

        if (userinput.getPassword() == null || userinput.getPassword().trim().isEmpty()) {
            model.addAttribute("error", "Password can't be empty");
            return "user/edit";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userinput.getPassword());
        userExist.setPassword(hashedPassword);

        if (userinput.getEmail() != null && !userExist.getEmail().equals(userinput.getEmail())) {
            if (userRepository.findByEmail(userinput.getEmail()) != null) {
                model.addAttribute("error", "Email already exists!");
                return "user/edit";
            }
            userExist.setEmail(userinput.getEmail());
        }

        if (userinput.getProfilepic() != null) {
            userExist.setProfilepic(userinput.getProfilepic());
        }

        userRepository.save(userExist);

        return "redirect:/user/all_users";
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        if (!userRepository.existsById(id)) {
            model.addAttribute("error", "User not found!");
            return "error/404";
        }

        userService.deleteUserById(id);
        return "redirect:/user/all_users";
    }
}
