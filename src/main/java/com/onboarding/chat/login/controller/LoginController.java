package com.onboarding.chat.login.controller;

import com.onboarding.chat.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    @GetMapping(value = {"/", ""})
    public String loginForm(@ModelAttribute UserDto userDto) {
        return "login/loginForm";
    }

    @PostMapping
    public String login(@ModelAttribute UserDto userDto) {
        return "redirect:/chatroom/list?nickname=" + userDto.getNickname();
    }

}
