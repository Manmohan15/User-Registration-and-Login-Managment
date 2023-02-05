package com.manmohan.LoginSystem.controller;

import com.manmohan.LoginSystem.dto.UserRegistrationDTO;
import com.manmohan.LoginSystem.entity.User;
import com.manmohan.LoginSystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationControlller {
    private UserService userService;

    public UserRegistrationControlller(UserService userService) {
        this.userService = userService;
    }

//    @ModelAttribute("user")
//    public UserRegistrationDTO userRegistrationDTO(){
//        return new UserRegistrationDTO();
//    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new UserRegistrationDTO());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO userRegistrationDTO)
    {
        userService.save(userRegistrationDTO);
        return "redirect:/registration?success";

    }
}
