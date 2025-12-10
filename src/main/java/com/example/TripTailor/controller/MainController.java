package com.example.TripTailor.controller;

import com.example.TripTailor.dto.response.CustomUserDetails;
import com.example.TripTailor.entity.User;
import com.example.TripTailor.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "redirect:/main";
    }

    @GetMapping("/signup")
    public String signUpPage(){
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

    @DeleteMapping("/deleteaccount")
    @ResponseBody // ✅ redirect 문자열을 그대로 응답 본문으로 내려줌
    public String deleteAccount(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        userService.deleteUser(userDetails.getUser().getUserCd());
        new SecurityContextLogoutHandler().logout(request, response, null);
        return "redirect";// 단순 텍스트 응답
    }

}
