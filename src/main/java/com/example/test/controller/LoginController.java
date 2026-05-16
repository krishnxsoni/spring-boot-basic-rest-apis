package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
    @GetMapping("/")
    public String defaultPage()
    {
        return "redirect:/login";
    }
    @GetMapping(value = "/login")
    public String getLoginPage(){
        return "login";
    }
}
