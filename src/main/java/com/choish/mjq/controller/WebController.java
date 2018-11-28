package com.choish.mjq.controller;

import com.choish.mjq.domain.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    UserRepository userRepository;

    @GetMapping("/")
    public String main(Model model){
        return "main";
    }
}
