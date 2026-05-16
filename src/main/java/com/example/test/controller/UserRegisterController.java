package com.example.test.controller;

import com.example.test.entities.User;
import com.example.test.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController
{

    private static final Logger log = LoggerFactory.getLogger(UserRegisterController.class);

    @GetMapping(value = "/new-user")
    public String returnNewUserRegistrationPage(){

        return "user-form";
    }


    @PostMapping(value = "/submit-user-details")
    public String createNewUser(@ModelAttribute User user, Model model)
    {
        log.info(":: Inside create-new-user Page :: ");
        String username  = user.getUserName();
        log.info(":: username :: "+username);
        model.addAttribute("username", username);
        return "submit-user-details";
    }
}
