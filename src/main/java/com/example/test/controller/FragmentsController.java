package com.example.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController
{
    @GetMapping(value = "fragment")
    public String renderFragments()
    {

        return "footer-impl";
    }
}
