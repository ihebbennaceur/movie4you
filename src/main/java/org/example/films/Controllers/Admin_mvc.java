package org.example.films.Controllers;

import org.example.films.Entitys.AdminEntity;
import org.example.films.Entitys.UserEntity;
import org.example.films.Repositories.AdminRepository;
import org.example.films.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Admin_mvc {


    @Autowired
    AdminService adminService;


    @RequestMapping("/create_admin")
    public String addAdmin(Model model) {
        AdminEntity adminEntity = new AdminEntity();

        model.addAttribute("adminForm", adminEntity);
        return "add_admin";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAdmin(@ModelAttribute("adminForm") AdminEntity adminEntity, Model model) {
        if (adminEntity.getUsername() == null || adminEntity.getUsername().trim().isEmpty() ||
                adminEntity.getPassword() == null || adminEntity.getPassword().trim().isEmpty()) {
            model.addAttribute("error", "Nom d'utilisateur et mot de passe sont obligatoires");
            return "add_admin";
        }

        if (adminRepository.findByUsername(adminEntity.getUsername()) != null) {
            model.addAttribute("error", "Le nom d'utilisateur existe déjà");
            return "add_admin";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(hashedPassword);

        adminService.adminCreate(adminEntity);
        return "redirect:/all_admins";
    }



    @Autowired
        AdminRepository adminRepository;
    @RequestMapping("/all_admins")
   public String all(Model model){
        List<AdminEntity> admins = adminRepository.findAll();
        model.addAttribute("admins", admins);
    return  "all_admins";}

    }

