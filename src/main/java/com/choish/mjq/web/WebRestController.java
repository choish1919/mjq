package com.choish.mjq.web;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    private Environment env;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
