package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username);

        // On vérifie si l'utilisateur existe et si le mot de passe correspond
        if (user != null && user.getPassword().equals(password)) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }

    // Route temporaire pour créer le premier admin (à supprimer après)
    @GetMapping("/create-admin")
    public String createAdmin() {
        if(userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("kayaye2026"); // Un mot de passe fort
            admin.setRole("Manager");
            userRepository.save(admin);
            return "Admin créé avec succès. Mot de passe : kayaye2026";
        }
        return "Admin existe déjà.";
    }
}