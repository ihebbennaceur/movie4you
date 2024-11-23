package com.example.demo.RestControllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.Entitys.AdminEntity;
import com.example.demo.Repositories.AdminRepository;
import com.example.demo.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")

public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;

    @GetMapping("/all_admins")
    public List<AdminEntity> getAllAdmins(){return adminRepository.findAll();}

    @PostMapping("/create_admin")
    public ResponseEntity<String> createAdmin(@Valid @RequestBody AdminEntity admin, BindingResult result){
        if (admin.getUsername() == null || admin.getUsername().trim().isEmpty()) {return ResponseEntity.noContent().build();}

        if(admin.getPassword() == null || admin.getPassword().trim().isEmpty()) {return ResponseEntity.noContent().build();}

     if (adminRepository.findByUsername(admin.getUsername()) != null){

     return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");}

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);

        adminRepository.save(admin);
        return ResponseEntity.ok("password changed");

    }


    @DeleteMapping("/delete_admin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Integer id){

        if (!adminRepository.existsById(id)){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found!");}

        adminService.deleteAdminById(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }

    @PutMapping("edit_admin/{id}")
    public ResponseEntity<String>  updatePassword(@PathVariable("id") Integer id, @RequestParam String newPassword){
        if (!adminRepository.existsById(id)){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found!");}
        if (newPassword ==null || newPassword.trim().isEmpty()){return ResponseEntity.noContent().build();}

        AdminEntity admin = adminRepository.findById(id).get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(newPassword);

        admin.setPassword(hashedPassword);
        adminRepository.save(admin);

        return ResponseEntity.ok("password changed");
        }

    @GetMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
        AdminEntity admin = adminRepository.findByUsername(username);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, admin.getPassword())) {

            return ResponseEntity.ok("admin logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }


    }

}
