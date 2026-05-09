package com.example.test.controller;

import com.example.test.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AboutController
{

    private static final Logger log = LoggerFactory.getLogger(AboutController.class);

    @GetMapping(value = "/new-user")
    public String returnNewUserRegistrationPage(){

        return "user-form";
    }


    @PostMapping(value = "submit-user-details")
    public String createNewUser(Model model)
    {
        log.info(":: Inside create-new-user Page :: ");
        String firstName  = model.getAttribute("firstName")!=null?(String)model.getAttribute("firstName"):Constants.EMPTY_STRING;
        log.info(":: firstName :: "+firstName);
        String lastName  = model.getAttribute("lastName")!=null?(String)model.getAttribute("lastName"):Constants.EMPTY_STRING;
        log.info(":: lastName :: "+lastName);
        String userName  = model.getAttribute("userName")!=null?(String)model.getAttribute("userName"):Constants.EMPTY_STRING;
        log.info(":: userName :: "+userName);
        String email  = model.getAttribute("email")!=null?(String)model.getAttribute("email"):Constants.EMPTY_STRING;
        log.info(":: email :: "+email);
        String mobileNum  = model.getAttribute("mobileNumber")!=null?(String)model.getAttribute("mobileNumber"):Constants.EMPTY_STRING;
        log.info(":: mobileNum :: "+mobileNum);

        return "";
    }
}
