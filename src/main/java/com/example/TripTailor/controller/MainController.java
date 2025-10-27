package com.example.TripTailor.controller;

import com.example.TripTailor.dto.response.CustomUserDetails;
import com.example.TripTailor.entity.User;
import com.example.TripTailor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/main")
    public String MainPage(){
        return "main";
    }

    @GetMapping("/login")
    public String LoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String SignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user) {
        userService.signUp(user); // 비밀번호 암호화 + 저장
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("nickname", userDetails.getUser().getNickname());
        return "mypage";
    }

}
