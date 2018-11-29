package com.choish.mjq.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // REST API, 즉 JSON Data를 리턴하는것이 목적
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줌
public class WebRestController {

    // HELLO WORLD!!!!!!
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
