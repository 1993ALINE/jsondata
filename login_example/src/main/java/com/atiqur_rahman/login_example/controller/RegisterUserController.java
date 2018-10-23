package com.atiqur_rahman.login_example.controller;

import com.atiqur_rahman.login_example.exception.UserAlreadyRegisterException;
import com.atiqur_rahman.login_example.model.User;
import com.atiqur_rahman.login_example.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by salem on 23/10/2018.
 */
@Controller
public class RegisterUserController {

    private UserServices userServices;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUserController.class);

    public RegisterUserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public String showRegisterUserPage(){
        return "regiser_new_user";
    }

    @PostMapping("/register")
    public String registerNewUser(@RequestParam("name")String name,
                                  @RequestParam("email")String email,
                                  @RequestParam("password")String password,
                                  RedirectAttributes redirectAttributes){

        LOGGER.info("register new user with name= {} and email={} and password={}",name,email,password);

        try {
            this.userServices.registerNewUser(name,email,password);
            redirectAttributes.addFlashAttribute("greenMessage","register Success please register now");

        } catch (UserAlreadyRegisterException e) {
            redirectAttributes.addFlashAttribute("redMessage",e.getMessage());
        }

        return "redirect:/login";
    }
}
