package com.example.demo.Controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.Entitys.AdminEntity;
import com.example.demo.Repositories.AdminRepository;
import com.example.demo.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")

public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;

    @GetMapping("/all_admins")
    public List<AdminEntity> getAllAdmins(){return adminRepository.findAll();}

    @PostMapping("/create_admin")
    public String createAdmin(@Valid @RequestBody AdminEntity admin, BindingResult result){
        if (admin.getUsername() == null || admin.getUsername().trim().isEmpty()) {return "Username cant be empty";}

        if(admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {return "Password cant be empty";}

     if (adminRepository.findByUsername(admin.getUsername()) != null){

     return "Username already exists!";}

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);

        adminRepository.save(admin);
        return "Admin created";

    }


    @DeleteMapping("/delete_admin/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id){

        if (!adminRepository.existsById(id)){return "admin not found";}

        adminService.deleteAdminById(id);
        return "Admin is deleted ";
    }

    @PutMapping("edit_admin/{id}")
    public String  updatePassword(@PathVariable("id") Integer id, @RequestParam String newPassword){
        if (!adminRepository.existsById(id)){return "admin not found";}
        if (newPassword ==null || newPassword.trim().isEmpty()){return "Password cant be empty";}

        AdminEntity admin = adminRepository.findById(id).get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(newPassword);

        admin.setPassword(hashedPassword);
        adminRepository.save(admin);
        return "Password updated";
        }

    @GetMapping("/admin/login")
    public String loginAdmin(@RequestParam String username, @RequestParam String password) {
        AdminEntity admin = adminRepository.findByUsername(username);
        if (admin == null) {
            return "admin not found";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, admin.getPassword())) {

            return "login success";
        } else {
            return "wrong username or password ";
        }


    }

}
