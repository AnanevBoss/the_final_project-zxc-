package com.example.springmodels;

import com.example.springmodels.models.User;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class registrationController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(User user, Model model) {
        User user_from_db = userRepository.findByLogin(user.getLogin());
        if (user_from_db != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }


        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        user.setRole("user");
        userRepository.save(user);
        return "redirect:/login";
    }

}
