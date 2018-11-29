package com.choish.mjq.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴하는 것이 목적
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줌
public class WebController {

    @GetMapping("/")
    public String main(Model model){
        return "main";
    }

}
