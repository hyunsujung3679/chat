package com.onboarding.chat.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String loginForm(@ModelAttribute String username) {
        return "login/loginForm";
    }

}
