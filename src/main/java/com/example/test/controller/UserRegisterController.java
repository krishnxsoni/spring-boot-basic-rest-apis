package com.example.test.controller;

import com.example.test.entities.User;
import com.example.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController
{
    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserRegisterController.class);

    @GetMapping(value = "/new-user")
    public String returnNewUserRegistrationPage(){

        return "user-form";
    }


    @PostMapping(value = "/submit-user-details")
    public String checkUserLogin(@ModelAttribute User user, Model model)
    {
        log.info(":: Inside create-new-user Page :: ");
        String username  = user.getUserName();
        log.info(":: username :: "+username);

        User user1  = userService.getUserByUsername(username);
        if(user1==null){
            return "user-not-found";
        }
        else{
            model.addAttribute("username", user1.getFirstName()+" "+user1.getLastName());
            return "submit-user-details";
        }
    }
}
